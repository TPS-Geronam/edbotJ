package commands.mariadb.devs;

import commands.interfaces.GeneralCommand;
import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

import java.awt.*;

public class comProcuratores implements GeneralCommand {
    private final String commandName = "jobs";

    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return !SharedComRequirements.checkSelf(event);
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

    public static void AddProcuratores() {
        Secrets.procuratores.put("programmator", "319861468705325057");
        Secrets.procuratores.put("historicus", "319861531879800832");
        Secrets.procuratores.put("scriptor", "810765049840009237");
        Secrets.procuratores.put("artifex", "321670679743430657");
    }

    @Override
    public String help() {
        return Secrets.prefix + commandName;
    }

    @Override
    public String longhelp() {
        return "Lists all current job roles/procuratores.";
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
