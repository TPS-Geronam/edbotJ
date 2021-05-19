package commands.mariadb.devs;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;

public class comVicari implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkSelf(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        StringBuilder vics = new StringBuilder();
        for (String key : Secrets.vicari.keySet()) {
            if (vics.toString().equals("")) {
                vics = new StringBuilder("Vicari: `" + key + "`");
            } else {
                vics.append(", `").append(key).append("`");
            }
        }
        ErrorHandler.CustomEmbed(vics.toString(), new Color(3, 193, 19), event);
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
