package util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.RestAction;

import java.awt.*;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class General {
    public static EmbedBuilder getInfoEmbed(ReadyEvent event, MessageReceivedEvent mevent, String title) throws ExecutionException, InterruptedException {
        JDA usedJDA;
        if (event == null) {
            usedJDA = mevent.getJDA();
        } else {
            usedJDA = event.getJDA();
        }
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(title);
        eb.setColor(new Color(3, 193, 19));
        eb.setFooter("edbotJ", usedJDA.getSelfUser().getAvatarUrl());
        eb.addField("System", "Platform: " + System.getProperty("os.name") + "\nVersion: " + System.getProperty("os.version") + "\nArch: " + System.getProperty("os.arch"), true);
        long runt = System.nanoTime() - Secrets.STARTTIME;
        long mem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        eb.addField("edbotJ", "Runtime: " + TimeUnit.HOURS.convert(runt, TimeUnit.NANOSECONDS) + "h" + "\nU.Memory: " + mem / 1000000 + "MB" + "\nVersion: " + Secrets.VERSION, true);
        eb.addField("Discord", "Guilds: " + CountGuilds(usedJDA.getGuilds()) + "\nUsers: " + CountUsers(usedJDA.getUsers()) + "\nPing: " + usedJDA.getGatewayPing() + "ms", true);
        RestAction<User> u = usedJDA.retrieveUserById(Secrets.OWNER);
        eb.addField("Info", "edbotJ was written by " + u.submit().get().getAsMention() + " with the help of [JDA 4.2](https://github.com/DV8FromTheWorld/JDA).", true);

        return eb;
    }

    public static int CountGuilds(java.util.List<Guild> guilds){
        int count = 0;
        for(int i = 0; i < guilds.size(); i++){
            count++;
        }
        return count;
    }

    public static int CountUsers(List<User> users){
        int count = 0;
        for(int i = 0; i < users.size(); i++){
            count++;
        }
        return count;
    }
}
