package commands.mariadb.playtest;

import com.google.gson.*;
import commands.interfaces.DBCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Objects;

public class comReqAllRequests implements DBCommand {
    private final String commandName = "lplayreq";

    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCuria(event) && !event.isFromType(ChannelType.PRIVATE);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        String zone = null;
        if (Args.length > 0) {
            String arg = Args[0].toLowerCase(Locale.ROOT);
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
        }

        String playtests = PlaytestReportmanager.GetPlaytestReqsFromDB(event, zone);
        JsonArray json = new Gson().fromJson(playtests, JsonArray.class);

        int totalFields = 1;
        LinkedList<LinkedList<String>> lPlaytests = new LinkedList<>();
        lPlaytests.add(new LinkedList<>());
        lPlaytests.get(0).add("```md\n[ZONE][NAME] by REQUESTOR: COMMENT\n```\n");

        for (JsonElement j : json) {
            JsonArray inner = j.getAsJsonArray();
            String curPlay = "```md\n[" + inner.get(1).getAsString() + "][" + inner.get(0).getAsString() + "] by " + inner.get(3).getAsString() + ": " + inner.get(4).getAsString() + "\n```";

            if (String.join("", lPlaytests.get(totalFields - 1)).length() + curPlay.length() > 1024) {
                totalFields++;
                lPlaytests.add(new LinkedList<>());
            }
            lPlaytests.get(totalFields - 1).add(curPlay);
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(new Color(3, 193, 19));
        eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
        eb.setTitle("Playtest Requests:");
        eb.setDescription("Use `" + Secrets.prefix + "lplayreq [zone]` to get a list of playtest requests only related to specified zone. (e.g. `" + Secrets.prefix + "lplayreq c`)");
        for (int i = 1; i <= lPlaytests.size(); i++) {
            eb.addField("Page " + i + ":", String.join("", lPlaytests.get(i - 1)), false);
        }
        event.getTextChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + commandName + " [zone]";
    }

    @Override
    public String longhelp() {
        return "Requests a list of all playtest requests from the database. `zone` can be" +
                " specified to only display requests for said zone.";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}