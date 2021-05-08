package commands.mariadb.projects;

import core.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comRemoveUserFromProject implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("546580860456009760"))) {
                Main.ErrorHandler.CustomEmbedError("You have to be a dev to be able to execute this command.", event);
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
            String username;

            try {
                if (Args[0].contains("@")) {
                    userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
                    username = event.getJDA().retrieveUserById(userid).complete().getName();

                    if (!userid.equals(event.getMessage().getAuthor().getId()) && !event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
                        Main.ErrorHandler.CustomEmbedError("Invalid user. Only Centurions and Quaestors can submit project applications for other people.", event);
                        return;
                    }
                } else {
                    Main.ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                    return;
                }
            }
            catch (Exception e) {
                Main.ErrorHandler.CustomEmbedError("Invalid user.", event);
                return;
            }

            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().getMemberById(event.getAuthor().getId()).getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
                Secrets.projectRemList.put(userid, new ProjectRemoveRequest(event, userid, username));

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setDescription(":white_check_mark: Your project dismissal application has been sent, " + event.getAuthor().getAsMention() +"! You will be DM'ed once your request has been reviewed by a Centurion, so do not leave the server.");
                event.getChannel().sendMessage(eb.build()).queue();

                eb.setTitle("Project dismissal application by " + event.getAuthor().getName() + ":");
                eb.setDescription("Profile: " + event.getAuthor().getAsMention());
                eb.setFooter(userid, event.getJDA().getSelfUser().getAvatarUrl());
                event.getJDA().getTextChannelById(Secrets.projectEndchan).sendMessage(eb.build()).queue();
            } else {
                PrjManager.DeleteUserFromProject(event, userid);
            }
        }
        catch (Exception e) {
            Main.ErrorHandler.CustomEmbedError("Wrong syntax.", event);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "prjremove <user>";
    }

    @Override
    public String longhelp() {
        return "Removes a user from any projects.";
    }
}
