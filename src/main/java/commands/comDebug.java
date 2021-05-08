package commands;

import core.CommandHandler;
import core.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;
import java.util.*;

public class comDebug implements Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getMessage().getAuthor().getId().equals(Secrets.ownerId)) {
                Main.ErrorHandler.CustomEmbedError("You have to be the owner to be able to execute this command.", event);
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
        Secrets.debug = !Secrets.debug;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(new Color(3, 193, 19));
        eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
        eb.setTitle("edbotJ:");
        eb.setDescription(":white_check_mark: Debug mode set to " + Secrets.debug + ".");
        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "debug";
    }

    @Override
    public String longhelp() {
        return "Toggles the debug mode of edbotJ. Only usable by the owner.";
    }
}
