package commands;

import core.CommandHandler;
import listeners.ReadyListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.General;
import util.Secrets;

import java.awt.*;
import java.util.ArrayList;

public class comInfo implements Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        try { event.getTextChannel().sendMessage(General.getInfoEmbed(null, event, "edbotJ:").build()).queue(); } catch (Exception ignored) { }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "info";
    }

    @Override
    public String longhelp() {
        return "Shows general info about the bot.";
    }
}
