package commands.general;

import commands.interfaces.AdminCommand;
import core.CommandHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;

public class comAdminHelp implements AdminCommand {
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCenturion(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        StringBuilder coms = new StringBuilder();

        if (Args.length > 0) {
            if (Args[0].equals("general")) {
                for (int i = 0; i < CommandHandler.commandsAdminHelpGeneral.size(); i++) {
                    coms.append(CommandHandler.commandsAdminHelpGeneral.get(i));
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setTitle("edbotJ General Admin Commands:");
                eb.setDescription("Required parameters: `<parameter>` , optional parameters: `[parameter]`");
                eb.addField("Page 1:", coms.toString(), false);

                event.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage(eb.build()).queue());

                EmbedBuilder ef = new EmbedBuilder();
                ef.setColor(new Color(3, 193, 19));
                ef.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                ef.setTitle("edbotJ:");
                ef.setDescription(":inbox_tray: The general admin commands have been sent to you via DM!");
                event.getTextChannel().sendMessage(ef.build()).queue();
            } else if (Args[0].equals("db")) {
                for (int i = 0; i < CommandHandler.commandsAdminHelpDB.size(); i++) {
                    coms.append(CommandHandler.commandsAdminHelpDB.get(i));
                }

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setTitle("edbotJ Database Admin Commands:");
                eb.setDescription("Required parameters: `<parameter>` , optional parameters: `[parameter]`");
                eb.addField("Page 1:", coms.toString(), false);

                event.getAuthor().openPrivateChannel().queue((channel) -> channel.sendMessage(eb.build()).queue());

                EmbedBuilder ef = new EmbedBuilder();
                ef.setColor(new Color(3, 193, 19));
                ef.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                ef.setTitle("edbotJ:");
                ef.setDescription(":inbox_tray: The database admin commands have been sent to you via DM!");
                event.getTextChannel().sendMessage(ef.build()).queue();
            }
        } else {
            for (int i = 0; i < CommandHandler.commandsAdminHelp.size(); i++) {
                coms.append(CommandHandler.commandsAdminHelp.get(i));
            }

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(new Color(3, 193, 19));
            eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTitle("edbotJ Admin Help:");
            eb.setDescription("Use `" + Secrets.prefix + "ahelp [type]` to further define the area of admin commands you want help with. (e.g. `" + Secrets.prefix + "ahelp db`)");
            eb.addField("Available admin `[type]` identifiers:", coms.toString(), false);
            event.getTextChannel().sendMessage(eb.build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "ahelp";
    }

    @Override
    public String longhelp() {
        return "Shows a list of available admin commands.";
    }
}
