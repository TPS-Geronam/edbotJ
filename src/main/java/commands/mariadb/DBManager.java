package commands.mariadb;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import util.Secrets;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;

public class DBManager {
    public static boolean EstablishConnection(MessageReceivedEvent event, String target) {
        try {
            URL script = new URL(Secrets.getDBHost() + target);
            URLConnection sc = script.openConnection();
            String userpass = Secrets.getDBAccess();
            String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userpass.getBytes()));
            sc.setRequestProperty ("Authorization", basicAuth);
            BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("successfully")) {
                    ErrorHandler.CustomEmbed(inputLine, new Color(3, 193, 19), event);
                    in.close();
                    return true;
                } else {
                    ErrorHandler.CustomEmbedError(inputLine, event);
                    in.close();
                    return false;
                }
            }
        }
        catch (Exception e) {
            if (Secrets.debug) {
                ErrorHandler.CustomEmbedError(e.getMessage(), event);
            }
        }
        return false;
    }
}
