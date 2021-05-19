package commands.mariadb.playtest;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;
import util.SharedComRequirements;

public class comAddReport implements commands.Command {
    @Override
    public boolean called(String[] Args, MessageReceivedEvent event) {
        return SharedComRequirements.checkCuria(event);
    }

    @Override
    public void action(String[] Args, MessageReceivedEvent event) {

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return Secrets.prefix + "aplay <<file> OR <link>>";
    }

    @Override
    public String longhelp() {
        return "Adds a playtest to the database.";
    }
}
