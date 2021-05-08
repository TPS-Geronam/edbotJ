package commands;

import com.sun.org.apache.xpath.internal.Arg;
import core.ErrorHandler;
import core.Main;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.StringUtils;
import util.Secrets;

import java.util.Arrays;

public class comSay implements Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        if (!event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId())) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById("337176399532130307")) && !event.getGuild().getMemberById(event.getAuthor().getId()).getRoles().contains(event.getGuild().getRoleById("489942850725871636"))) {
                ErrorHandler.CustomEmbedError("You have to be a Centurion or Quaestor to be able to execute this command.", event);
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
            TextChannel c;
            if (Args[0].contains("#")) {
                String chan = Args[0].replace("<", "").replace(">", "").replace("#", "");
                c = event.getGuild().getTextChannelById(chan);
            }
            else {
                c = event.getGuild().getTextChannelsByName(Args[0], true).get(0);
            }
            c.sendMessage(StringUtils.join(Arrays.copyOfRange(Args, 1, Args.length), " ")).queue();
        }
        catch (Exception e) {
            ErrorHandler.CustomEmbedError("Predefined channel not valid.", event);
        }
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "say <channel> <message>";
    }

    @Override
    public String longhelp() {
        return "Displays a message sent by edbotJ in the predefined text channel.";
    }
}
