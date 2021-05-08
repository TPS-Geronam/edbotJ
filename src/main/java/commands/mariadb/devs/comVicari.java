package commands.mariadb.devs;

import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comVicari implements commands.Command{
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
        String vics = "";
        for (String key : Secrets.vicari.keySet()) {
            if (vics.equals("")) {
                vics = "Vicari: `" + key + "`";
            } else {
                vics = vics + ", `" + key + "`";
            }
        }
        Main.ErrorHandler.CustomEmbed(vics, new Color(3, 193, 19), event);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "vicari";
    }

    @Override
    public String longhelp() {
        return "Lists all current vicari.";
    }
}
