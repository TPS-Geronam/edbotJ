package commands.mariadb.devs;

import commands.interfaces.AdminCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

public class comRemoveDev implements AdminCommand {
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
        DevManager.DeleteDevFromDB(event, userid);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "rdev <user>";
    }

    @Override
    public String longhelp() {
        return "Manually removes a user from the dev database.";
    }
}
