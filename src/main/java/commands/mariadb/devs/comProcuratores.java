package commands.mariadb.devs;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;

public class comProcuratores implements commands.Command{
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkSelf(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {
        StringBuilder procs = new StringBuilder();
        for (String key : Secrets.procuratores.keySet()) {
            if (procs.toString().equals("")) {
                procs = new StringBuilder("Procuratores: `" + key + "`");
            } else {
                procs.append(", `").append(key).append("`");
            }
        }
        ErrorHandler.CustomEmbed(procs.toString(), new Color(3, 193, 19), event);
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "procuratores";
    }

    @Override
    public String longhelp() {
        return "Lists all current procuratores.";
    }
}
