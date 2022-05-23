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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandBucket {
    private final List<Command> commandsGeneral;
    private final List<Command> commandsDev;
    private final List<Command> commandsAdmin;

    public CommandBucket() {
        commandsGeneral = Stream.of(
            new comHelp(),
            new comInfo(),
            new comAddHiatus(),
            new comRemoveHiatus(),
            new comUpdateHiatus(),
            new comVicari(),
            new comProcuratores()

        ).collect(Collectors.toList());

        commandsDev = Stream.of(
            new comAddUserToProject(),
            new comRemoveUserFromProject(),
            new comReqReport(),
            new comReqAllReports(),
            new comRemReport(),
            new comAddReport(),
            new comReqAllRequests(),
            new comRemRequest(),
            new comAddRequest()
        ).collect(Collectors.toList());

        commandsAdmin = Stream.of(
            new comAdminHelp(),
            new comSay(),
            new comDebug(),
            new comAddDev(),
            new comRemoveDev(),
            new comAddAllDevs(),
            new comClearDevs(),
            new comAddProject(),
            new comDeleteProject()
        ).collect(Collectors.toList());
    }

    public List<Command> getCommandsGeneral() {
        return commandsGeneral;
    }

    public List<Command> getCommandsDev() {
        return commandsDev;
    }

    public List<Command> getCommandsAdmin() {
        return commandsAdmin;
    }
}