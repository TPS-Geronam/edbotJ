package commands.mariadb.devs;

import commands.mariadb.DBManager;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DevManager {
    public static boolean ClearDevsDB(MessageReceivedEvent event) {
        String target = "/Pub%20Ed/scripts/resetDevs.php";
        return DBManager.EstablishConnection(event, target);
    }

    public static boolean AddDevToDB(MessageReceivedEvent event, String userid, String username, String vicarius, boolean ret) {
        String target = "/Pub%20Ed/scripts/postDev.php?userid=" + userid + "&username=" + username.replace(" ", "%20") + "&vicarius=" + vicarius.replace(" ", "%20");
        return DBManager.EstablishConnection(event, target);
    }

    public static boolean DeleteDevFromDB(MessageReceivedEvent event, String userid) {
        String target = "/Pub%20Ed/scripts/deleteDev.php?userid=" + userid;
        return DBManager.EstablishConnection(event, target);
    }
}