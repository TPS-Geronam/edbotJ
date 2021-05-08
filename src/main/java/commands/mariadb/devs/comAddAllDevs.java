package commands.mariadb.devs;

import core.ErrorHandler;
import core.Main;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import util.Secrets;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

public class comAddAllDevs implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().getMemberById(event.getAuthor().getId()).getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
                ErrorHandler.CustomEmbedError("You have to be a Centurion or Quaestor to be able to execute this command.", event);
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
        boolean ret = true;
        try {
            if (Args.length > 0) {
                ret = Boolean.parseBoolean(Args[0]);
            }
        } catch (Exception e) {
            ErrorHandler.CustomEmbedError("`hide` is not boolean. Use either `true` or `false`.", event);
            return;
        }
        DevManager.ClearDevsDB(event);
        Guild g = event.getGuild();
        Role r = g.getRoleById("546580860456009760");
        //List<Member> l = new ArrayList<>();

        if (ret) {
            g.loadMembers(member -> {
                if (member.getRoles().contains(r)) {
                    work(event, member, true);
                }
            });
        } else {
            g.loadMembers(member -> {
                if (member.getRoles().contains(r)) {
                    work(event, member, false);
                }
            });
        }

        /*for (Member m : l) {
            System.out.println(m.getEffectiveName());
            List<Role> lr = m.getRoles();
            String vic = "None";
            for (Role mr : lr) {
                if (StringUtils.containsIgnoreCase(mr.getName(), "Zone")) {
                    vic = mr.getName();
                    break;
                }
            }
            Main.DevManager.AddDevToDB(event, m.getUser().getId(), m.getUser().getName(), vic, ret);
        }*/
        ErrorHandler.CustomEmbed(":white_check_mark: Task completed.", new Color(3, 193, 19), event);
    }

    public void work(MessageReceivedEvent event, Member m, boolean ret) {
        List<Role> lr = m.getRoles();
        String vic = "None";
        for (Role mr : lr) {
            if (StringUtils.containsIgnoreCase(mr.getName(), "Zone")) {
                vic = mr.getName();
                break;
            }
        }
        DevManager.AddDevToDB(event, m.getUser().getId(), m.getUser().getName(), vic, ret);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "devall [hide]";
    }

    @Override
    public String longhelp() {
        return "Adds all devs to the dev database by clearing it first and pulling all devs after. Use with caution! `[hide]` boolean decides whether the bot should hide success messages (default `true`).";
    }
}
