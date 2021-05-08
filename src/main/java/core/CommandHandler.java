package core;

import commands.Command;
import util.Secrets;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHandler {
    public static final CommandParser parse = new CommandParser();
    public static HashMap<String, Command> commands = new HashMap<String, Command>();

    public static ArrayList<String> commandsHelp = new ArrayList<String>();
    public static ArrayList<String> commandsHelpGeneral = new ArrayList<String>();
    public static ArrayList<String> commandsHelpDB = new ArrayList<String>();

    public static ArrayList<String> commandsAdminHelp = new ArrayList<String>();
    public static ArrayList<String> commandsAdminHelpGeneral = new ArrayList<String>();
    public static ArrayList<String> commandsAdminHelpDB = new ArrayList<String>();

    public static void HandleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);
            if (!safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
            else {
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
        else {
            if (Secrets.debug) {
                Main.ErrorHandler.CustomEmbedError("Command *" + Secrets.prefix + cmd.invoke.toString() + "* does not exist.", cmd.event);
            }
        }
    }
}
