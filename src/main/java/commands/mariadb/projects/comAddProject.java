package commands.mariadb.projects;

import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

public class comAddProject implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().getMemberById(event.getAuthor().getId()).getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
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
        String projectname;
        String projectdesc;
        String projectowner;

        if (Args.length > 0) {
            projectid = Args[0];
        } else {
            Main.ErrorHandler.CustomEmbedError("Invalid project ID.", event);
            return;
        }
        if (Args.length > 1) {
            projectname = Args[1];
        } else {
            Main.ErrorHandler.CustomEmbedError("Invalid project name.", event);
            return;
        }
        if (Args.length > 2) {
            projectdesc = Args[2];
        } else {
            Main.ErrorHandler.CustomEmbedError("Invalid project description.", event);
            return;
        }
        projectowner = event.getMessage().getAuthor().getName();

        PrjManager.AddProject(event, projectid, projectname, projectdesc, projectowner);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "addproject <projectid> <projectname> <projectdesc>";
    }

    @Override
    public String longhelp() {
        return "Creates a new project. Note that `projectid` is to be specified without the `p_` prefix.";
    }
}
