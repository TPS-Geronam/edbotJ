package commands.mariadb.devs;

import commands.interfaces.AdminCommand;
import commands.interfaces.DBCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import util.Secrets;
import util.SharedComRequirements;

import java.util.List;

public class comAddDev implements AdminCommand, DBCommand {
    private final String commandName = "devall";

    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCenturion(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        String userid;
        String username;
        try {
            if (Args[0].contains("@")) {
                userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
                username = event.getJDA().retrieveUserById(userid).complete().getName();
            } else {
                ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                return;
            }
        } catch (Exception e) {
            ErrorHandler.CustomEmbedError("Invalid user.", event);
            return;
        }
        List<Role> l = event.getGuild().retrieveMemberById(userid).complete().getRoles();
        String vic = "None";
        for (Role mr : l) {
            if (StringUtils.containsIgnoreCase(mr.getName(), "Zone")) {
                vic = mr.getName();
                break;
            }
        }
        DevManager.AddDevToDB(event, userid, username, vic, false);
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
        return "Manually adds a user to the dev database.";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
