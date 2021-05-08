package commands.mariadb.projects;

import commands.mariadb.DBManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class PrjManager {
    public static boolean AddProject(MessageReceivedEvent event, String projectid, String projectname, String projectdesc, String projectowner) {
        String target = "/Pub%20Ed/scripts/postProject.php?projectid=" + projectid.replace(" ", "_") + "&projectname=" + projectname.replace(" ", "%20") +"&projectdesc=" + projectdesc.replace(" ", "%20") + "&projectowner=" + projectowner.replace(" ", "%20");
        return DBManager.EstablishConnection(event, target);
    }

    public static boolean DeleteProject(MessageReceivedEvent event, String projectid) {
        String target = "/Pub%20Ed/scripts/deleteProject.php?projectid=" + projectid.replace(" ", "_");
        return DBManager.EstablishConnection(event, target);
    }

    public static boolean AddUserToProject(MessageReceivedEvent event, String projectid, String userid, String username, String comment) {
        String target = "/Pub%20Ed/scripts/postUserToProject.php?projectid=" + projectid.replace(" ", "_") + "&userid=" + userid + "&username=" + username.replace(" ", "%20") + "&comment=" + comment.replace(" ", "%20");
        return DBManager.EstablishConnection(event, target);
    }

    public static boolean DeleteUserFromProject(MessageReceivedEvent event, String userid) {
        String target = "/Pub%20Ed/scripts/deleteUser.php?userid=" + userid;
        return DBManager.EstablishConnection(event, target);
    }
}