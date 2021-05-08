package commands.mariadb.hiatuses;

import core.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comRemoveHiatus implements commands.Command{
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

            try {
                if (Args[0].contains("@") && Args[0].contains("<")) {
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
        return Secrets.prefix + "hremove <user>";
    }

    @Override
    public String longhelp() {
        return "Removes a hiatus record from the database. Deleting other people's records is only possible for Centurions.";
    }
}
