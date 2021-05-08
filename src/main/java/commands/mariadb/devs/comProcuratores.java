package commands.mariadb.devs;

import core.Main;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;

public class comProcuratores implements commands.Command{
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
        String procs = "";
        for (String key : Secrets.procuratores.keySet()) {
            if (procs.equals("")) {
                procs = "Procuratores: `" + key + "`";
            } else {
                procs = procs + ", `" + key + "`";
            }
        }
        Main.ErrorHandler.CustomEmbed(procs, new Color(3, 193, 19), event);
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
