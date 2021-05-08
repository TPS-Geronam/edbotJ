package core;

import commands.Command;
import util.Secrets;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandHandler {
    public static HashMap<String, Command> commands = new HashMap<>();

    public static ArrayList<String> commandsHelp = new ArrayList<>();
    public static ArrayList<String> commandsHelpGeneral = new ArrayList<>();
    public static ArrayList<String> commandsHelpDB = new ArrayList<>();

    public static ArrayList<String> commandsAdminHelp = new ArrayList<>();
    public static ArrayList<String> commandsAdminHelpGeneral = new ArrayList<>();
    public static ArrayList<String> commandsAdminHelpDB = new ArrayList<>();

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
                ErrorHandler.CustomEmbedError("Command *" + Secrets.prefix + cmd.invoke + "* does not exist.", cmd.event);
            }
        }
    }
}
