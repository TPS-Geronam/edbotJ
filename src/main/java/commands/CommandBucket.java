package commands;

import commands.general.*;
import commands.interfaces.Command;
import commands.mariadb.devs.*;
import commands.mariadb.hiatuses.comAddHiatus;
import commands.mariadb.hiatuses.comRemoveHiatus;
import commands.mariadb.hiatuses.comUpdateHiatus;
import commands.mariadb.playtest.*;
import commands.mariadb.projects.comAddProject;
import commands.mariadb.projects.comAddUserToProject;
import commands.mariadb.projects.comDeleteProject;
import commands.mariadb.projects.comRemoveUserFromProject;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandBucket {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandBucket() {
        fillBucket(
            new comHelp(),
            new comInfo(),
            new comAddHiatus(),
            new comRemoveHiatus(),
            new comUpdateHiatus(),
            new comVicari(),
            new comProcuratores()
        );

        fillBucket(
            new comAddUserToProject(),
            new comRemoveUserFromProject(),
            new comReqReport(),
            new comReqAllReports(),
            new comRemReport(),
            new comAddReport(),
            new comReqAllRequests(),
            new comRemRequest(),
            new comAddRequest()
        );

        fillBucket(
            new comAdminHelp(),
            new comSay(),
            new comDebug(),
            new comAddDev(),
            new comRemoveDev(),
            new comAddAllDevs(),
            new comClearDevs(),
            new comAddProject(),
            new comDeleteProject()
        );
    }

    private void fillBucket(Command... cc) {
        Stream.of(cc).collect(Collectors.toList()).forEach(c -> {
            commands.put(c.getCommandName(), c);
        });
    }

    public Map<String, Command> getCommands() {
        return commands;
    }
}