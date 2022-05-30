package commands.mariadb.playtest;

import commands.interfaces.DBCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.*;

import java.awt.*;
import java.util.Locale;
import java.util.Objects;

public class comAddRequest implements DBCommand {
    private final String commandName = "aplayreq";

    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCuria(event) && !event.isFromType(ChannelType.PRIVATE);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        String name;
        String zone;
        String zoneRaw;
        String userid = event.getAuthor().getId();
        String username = event.getAuthor().getName();
        String comment = "";

        if (Args.length > 0) {
            String arg = Args[0].toLowerCase(Locale.ROOT);
            zoneRaw = arg;
            if (Secrets.vicari.containsKey(arg)) {
                try {
                    zone = Objects.requireNonNull(event.getGuild().getRoleById(Secrets.vicari.get(arg))).getName();
                } catch (NullPointerException e) {
                    ErrorHandler.CustomEmbedError("Could not get role name by id.", event);
                    event.getChannel().sendMessage("<@" + Secrets.OWNER + ">").queue();
                    return;
                }
            } else {
                ErrorHandler.CustomEmbedError("Zone name not found. See `" + Secrets.prefix + "zones` for a list of all zones.", event);
                return;
            }
        } else {
            ErrorHandler.CustomEmbedError("Invalid zone name. See `" + Secrets.prefix + "zones` for a list of all zones.", event);
            return;
        }
        if (Args.length > 1) {
            name = Args[1];
        } else {
            ErrorHandler.CustomEmbedError("Invalid playtest name.", event);
            return;
        }
        if (Args.length > 2) {
            comment = Args[2];
        }

        boolean added = PlaytestReportmanager.AddPlaytestReqToDB(event, name, zone, userid, username, comment);
        if (added) {
            TextChannel chan = (TextChannel)Objects.requireNonNull(event.getGuild().getGuildChannelById("810768564218232852")); // auditor

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(new Color(3, 193, 19));
            eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
            eb.setTitle("New Playtest Request Added for " + zone);
            eb.setDescription("Submit a playtest for this request with `" + Secrets.prefix + "aplay <...>` when ready (for syntax see `" + Secrets.prefix + "help db`).");
            eb.addField(name, "by " + username + "\n\n" + comment, false);

            chan.sendMessage(eb.build()).queue();
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + commandName + " <zone> <name> [comment]";
    }

    @Override
    public String longhelp() {
        return "Adds a playtest request to the database.";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}