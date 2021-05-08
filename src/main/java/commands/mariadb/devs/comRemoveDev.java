package commands.mariadb.devs;

import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

public class comRemoveDev implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
                Main.ErrorHandler.CustomEmbedError("You have to be a Centurion or Quaestor to be able to execute this command.", event);
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
        String userid;
        try {
            if (Args[0].contains("@")) {
                userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
            } else {
                Main.ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                return;
            }
        }
        catch (Exception e) {
            Main.ErrorHandler.CustomEmbedError("Invalid user.", event);
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
