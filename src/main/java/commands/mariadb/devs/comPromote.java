package commands.mariadb.devs;

import commands.interfaces.DBCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;
import java.util.List;

public class comPromote implements DBCommand {
    private final String commandName = "promote";

    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCenturion(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        String userid;
        try {
            if (Args[0].contains("@")) {
                userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
            } else {
                ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                return;
            }
        } catch (Exception e) {
            ErrorHandler.CustomEmbedError("Invalid user.", event);
            return;
        }
        Member m = event.getGuild().retrieveMemberById(userid).complete();
        List<Role> mr = m.getRoles();
        Role[] sr = new Role[4];
        sr[0] = event.getGuild().getRoleById(Secrets.CIVITATE);
        sr[1] = event.getGuild().getRoleById(Secrets.SENATE);
        sr[2] = event.getGuild().getRoleById(Secrets.DISCIPLIO);
        sr[3] = event.getGuild().getRoleById(Secrets.CURIA);

        //not a civitate
        if (!mr.contains(sr[0])) {
            event.getGuild().addRoleToMember(m.getIdLong(), sr[0]).queue();
            //event.getGuild().getController().addRolesToMember(m, sr[0]).queue();
            ErrorHandler.CustomEmbed(":white_check_mark: " + sr[0].getName() + " added to " + m.getEffectiveName() + ".", new Color(3, 193, 19), event);
        }
        //not a senate
        else if (!mr.contains(sr[1])) {
            event.getGuild().addRoleToMember(m.getIdLong(), sr[1]).queue();
            //event.getGuild().getController().addRolesToMember(m, sr[1]).queue();
            ErrorHandler.CustomEmbed(":white_check_mark: " + sr[1].getName() + " added to " + m.getEffectiveName() + ".", new Color(3, 193, 19), event);
        }
        //not a disciplio
        else if (!mr.contains(sr[2]) && !mr.contains(sr[3])) {
            String vicid = "";
            if (Args.length > 1) {
                vicid = Secrets.vicari.get(Args[1].toLowerCase());
                if (vicid == null) {
                    ErrorHandler.CustomEmbedError("Vicarius " + Args[1] + " does not exist. Refer to `" + Secrets.prefix + "vicari`.", event);
                    return;
                }
            } else {
                ErrorHandler.CustomEmbedError("Invalid vicarius. Refer to `" + Secrets.prefix + "vicari`.", event);
                return;
            }
            event.getGuild().addRoleToMember(m.getIdLong(), sr[2]).queue();
            event.getGuild().addRoleToMember(m.getIdLong(), sr[3]).queue();
            event.getGuild().addRoleToMember(m.getIdLong(), event.getGuild().getRoleById(vicid)).queue();
            //event.getGuild().getController().addRolesToMember(m, sr[2], sr[3], event.getGuild().getRoleById(vicid)).queue();
            ErrorHandler.CustomEmbed(":white_check_mark: " + sr[2].getName() + ", " + sr[3].getName() + " and Vicarius added to " + m.getEffectiveName() + ".", new Color(3, 193, 19), event);
            DevManager.AddDevToDB(event, userid, m.getUser().getName(), event.getGuild().getRoleById(vicid).getName(), false);
        }
        //not a dev
        else if (mr.contains(sr[2]) && mr.contains(sr[3])) {
            String procid = "";
            if (Args.length > 1) {
                procid = Secrets.procuratores.get(Args[1].toLowerCase());
                if (procid == null) {
                    ErrorHandler.CustomEmbedError("Procuratores " + Args[1] + " does not exist. Refer to `" + Secrets.prefix + "procuratores`.", event);
                    return;
                }
            } else {
                ErrorHandler.CustomEmbedError("Invalid procuratores. Refer to `" + Secrets.prefix + "procuratores`.", event);
                return;
            }
            event.getGuild().removeRoleFromMember(m.getIdLong(), sr[2]).queue();
            event.getGuild().addRoleToMember(m.getIdLong(), event.getGuild().getRoleById(procid)).queue();
            //event.getGuild().getController().removeSingleRoleFromMember(m, sr[2]).queue();
            //event.getGuild().getController().addSingleRoleToMember(m, event.getGuild().getRoleById(procid)).queue();
            ErrorHandler.CustomEmbed(":white_check_mark: " + event.getGuild().getRoleById(procid).getName() + " added to " + m.getEffectiveName() + ".", new Color(3, 193, 19), event);
        }
        //reached end
        else {
            ErrorHandler.CustomEmbedError("Cannot promote " + m.getEffectiveName() + " any further.", event);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + commandName + " <user>";
    }

    @Override
    public String longhelp() {
        return "Promotes a user.";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
