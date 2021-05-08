package commands.mariadb.hiatuses;

import core.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comAddHiatus implements commands.Command{
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
            String reason;
            String comment = "";
            String start;
            String end;
            try {
                if (Args[0].contains("@")) {
                    userid = Args[0].replace("<", "").replace(">", "").replace("@", "").replace("!", "");
                    username = event.getJDA().retrieveUserById(userid).complete().getName();
                } else {
                    Main.ErrorHandler.CustomEmbedError("Invalid user. Use `@Username` (ping).", event);
                    return;
                }
            }
            catch (Exception e) {
                Main.ErrorHandler.CustomEmbedError("Invalid user.", event);
                return;
            }
            if (Args.length > 1 && Args[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
                start = Args[1];
            } else {
                Main.ErrorHandler.CustomEmbedError("Invalid start date. Use `YYYY-MM-DD`.", event);
                return;
            }
            if (Args.length > 2 && Args[2].matches("\\d{4}-\\d{2}-\\d{2}")) {
                end = Args[2];
            } else {
                Main.ErrorHandler.CustomEmbedError("Invalid end date. Use `YYYY-MM-DD`.", event);
                return;
            }
            if (start.equals(end)) {
                Main.ErrorHandler.CustomEmbedError("`start` and `end` cannot be the same.", event);
                return;
            }
            if (Args.length > 3) {
                reason = Args[3];
            } else {
                Main.ErrorHandler.CustomEmbedError("Invalid reason.", event);
                return;
            }
            if (Args.length > 4) {
                comment = Args[4];
            }

            //337176399532130307: centurion
            /*if (!event.getGuild().getMemberById(event.getAuthor().getId()).getRoles().contains(event.getGuild().getRoleById("337176399532130307"))) {
                Secrets.hiatusList.put(userid, new HiatusRequest(event, userid, username, reason, comment, start, end));

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(new Color(3, 193, 19));
                eb.setFooter("edbotJ", event.getJDA().getSelfUser().getAvatarUrl());
                eb.setDescription(":white_check_mark: Your hiatus request has been sent, " + event.getAuthor().getAsMention() +"! You will be DM'ed once your request has been reviewed by a Centurion, so do not leave the server.");
                event.getChannel().sendMessage(eb.build()).queue();

                eb.setTitle("Hiatus requested by " + event.getAuthor().getName() + ":");
                eb.setDescription("Profile: " + event.getAuthor().getAsMention());
                eb.setFooter(userid, event.getJDA().getSelfUser().getAvatarUrl());
                eb.addField("Start", start, true);
                eb.addField("End", end, true);
                eb.addField("Reason", "\"" + reason + "\"", false);
                if (!comment.equals("")) {
                    eb.addField("Comment", "\"" + comment + "\"", false);
                }
                event.getJDA().getTextChannelById(Secrets.hiatusEndchan).sendMessage(eb.build()).queue();
            } else {*/
                HiatusManager.AddHiatusToDB(event, userid, username, reason, end, start, comment);
            //}
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
        return Secrets.prefix + "hiatus <user> <start date> <end date> <reason> [comment]";
    }

    @Override
    public String longhelp() {
        return "Adds a hiatus record to the database. Dates have to be in `YYYY-MM-DD` format. `reason` and `comment` have to be inside \"s.";
    }
}
