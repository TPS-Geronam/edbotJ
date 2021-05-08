package commands;

import core.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.General;
import util.Secrets;

import java.awt.*;
import java.util.*;

public class comHelp implements Command{
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
        String coms = "";

        if (Args.length > 0) {
            if (Args[0].equals("general")) {
                for (int i = 0; i < CommandHandler.commandsHelpGeneral.size(); i++) {
                    coms = coms + CommandHandler.commandsHelpGeneral.get(i);
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setTitle("edbotJ General Commands:");
                eb.setDescription("Required parameters: `<parameter>` , optional parameters: `[parameter]`");
                eb.addField("Page 1:", coms, false);

                event.getAuthor().openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(eb.build()).queue();
                });

                EmbedBuilder ef = new EmbedBuilder();
                ef.setColor(new Color(3, 193, 19));
                ef.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                ef.setTitle("edbotJ:");
                ef.setDescription(":inbox_tray: The general commands have been sent to you via DM!");
                event.getTextChannel().sendMessage(ef.build()).queue();
            } else if (Args[0].equals("db")) {
                for (int i = 0; i < CommandHandler.commandsHelpDB.size(); i++) {
                    coms = coms + CommandHandler.commandsHelpDB.get(i);
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setTitle("edbotJ Database Commands:");
                eb.setDescription("Required parameters: `<parameter>` , optional parameters: `[parameter]`");
                eb.addField("Page 1:", coms, false);

                event.getAuthor().openPrivateChannel().queue((channel) -> {
                    channel.sendMessage(eb.build()).queue();
                });

                EmbedBuilder ef = new EmbedBuilder();
                ef.setColor(new Color(3, 193, 19));
                ef.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                ef.setTitle("edbotJ:");
                ef.setDescription(":inbox_tray: The database commands have been sent to you via DM!");
                event.getTextChannel().sendMessage(ef.build()).queue();
            }
        } else {
            for (int i = 0; i < CommandHandler.commandsHelp.size(); i++) {
                coms = coms + CommandHandler.commandsHelp.get(i);
            }

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(new Color(3, 193, 19));
            eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTitle("edbotJ Help:");
            eb.setDescription("Use `" + Secrets.prefix + "help [type]` to further define the area of commands you want help with. (e.g. `" + Secrets.prefix + "help db`)");
            eb.addField("Available `[type]` identifiers:", coms, false);
            event.getTextChannel().sendMessage(eb.build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "help [type]";
    }

    @Override
    public String longhelp() {
        return "Shows a list of publicly available commands. Use `[type]` to further define the area of commands you want help with.";
    }
}
