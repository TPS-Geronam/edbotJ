package commands.mariadb.projects;

import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comDeleteProject implements commands.Command{
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
        String projectid;

        if (Args.length > 0) {
            projectid = Args[0];
        } else {
            Main.ErrorHandler.CustomEmbedError("Invalid project ID.", event);
            return;
        }
        if (Args.length > 1) {
            if (Args[1].equals("confirm")) {
                PrjManager.DeleteProject(event, projectid);
            }
        } else {
            Main.ErrorHandler.CustomEmbed(":warning: Warning! You are about to delete a project! To confirm your decision, execute `ed!delproject <projectid> confirm`.", new Color(193, 114, 0), event);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "delproject <projectid>";
    }

    @Override
    public String longhelp() {
        return "Deletes a project. Note that `projectid` is to be specified without the `p_` prefix.";
    }
}
