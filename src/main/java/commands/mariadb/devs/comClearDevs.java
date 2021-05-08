package commands.mariadb.devs;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

public class comClearDevs implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
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
        DevManager.ClearDevsDB(event);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "devclear";
    }

    @Override
    public String longhelp() {
        return "Resets the dev database. Use with caution!";
    }
}
