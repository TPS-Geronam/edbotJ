package commands.mariadb.hiatuses;

import core.ErrorHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;
import java.util.List;

public class comRemoveHiatus implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("546580860456009760"))) {
                ErrorHandler.CustomEmbedError("You have to be a dev to be able to execute this command.", event);
                return true;
            }
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        try {
            String userid;

            try {
                if (Args[0].contains("@") && Args[0].contains("<")) {
                    userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
                } else {
                    ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                    return;
                }
            }
            catch (Exception e) {
                ErrorHandler.CustomEmbedError("Invalid user.", event);
                return;
            }

            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307"))) {
                if (event.getAuthor().getId().equals(userid)) {
                    HiatusManager.RemoveHiatusFromDB(event, userid);
                } else {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setColor(new Color(200, 0, 0));
                    eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                    eb.setDescription("You can only delete your own hiatus record.");
                    event.getChannel().sendMessage(eb.build()).queue();
                }
            } else {
                HiatusManager.RemoveHiatusFromDB(event, userid);
            }

            Member m = event.getGuild().retrieveMemberById(userid).complete();
            List<Role> mr = m.getRoles();
            Role r = event.getGuild().getRoleById("363726364605677571");
            if (mr.contains(r)) {
                event.getGuild().removeRoleFromMember(m.getIdLong(), r).queue();
                ErrorHandler.CustomEmbed(":white_check_mark: " + "Removed " + r.getName() + " role.", new Color(3, 193, 19), event);
            }
        }
        catch (Exception e) {
            ErrorHandler.CustomEmbedError("Wrong syntax.", event);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "hremove <user>";
    }

    @Override
    public String longhelp() {
        return "Removes a hiatus record from the database. Deleting other people's records is only possible for Centurions.";
    }
}
