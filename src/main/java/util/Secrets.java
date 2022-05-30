package util;

import commands.mariadb.projects.ProjectAddRequest;
import commands.mariadb.projects.ProjectRemoveRequest;
import org.apache.commons.lang3.SystemUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class Secrets {
    public static final String CENTURION = "337176399532130307";
    public static final String SENIOR = "825956520374173704";
    public static final String JUNIOR = "755076726480044096";
    public static final String DISCIPLIO = "358939888386703371";
    public static final String CURIA = "546580860456009760";
    public static final String SENATE = "337170798592917506";
    public static final String CIVITATE = "337164158489591809";
    public static final String HIATUS = "363726364605677571";

    public static final String OWNER = "212693204519223296";
    public static final String ADMINCHAN = "532399694442266656";

    public static final String NODEGUILD = "318668421719916545"; //W:286194821394071552 E:318668421719916545
    public static final long STARTTIME = System.nanoTime();
    public static final String VERSION = "1.2.3";

    public static String prefix = "ed!";
    public static boolean debug = false;
    public static HashMap<String, String> vicari = new HashMap<>();
    public static HashMap<String, String> vicariChans = new HashMap<>();
    public static HashMap<String, String> procuratores = new HashMap<>();

    //Hiatus
    //public static HashMap<String, HiatusRequest> hiatusList = new HashMap<>();
    //public static String hiatusEndchan = ADMINCHAN;

    //Projects
    public static final HashMap<String, ProjectAddRequest> projectReqList = new HashMap<>();
    public static final HashMap<String, ProjectRemoveRequest> projectRemList = new HashMap<>();
    public static String projectEndchan = ADMINCHAN;

    private static Properties readProperties() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis;
        if (SystemUtils.IS_OS_LINUX) {
            fis = new FileInputStream("/opt/edbotj_dbot/prop.xml");
        } else {
            fis = new FileInputStream("prop.xml");
        }
        properties.loadFromXML(fis);

        return properties;
    }

    public static String getTokenM() throws Exception {
        return readProperties().getProperty("tokenM");
    }

    public static String getTokenB() throws Exception {
        return readProperties().getProperty("tokenB");
    }

    public static String getDBAccess() throws Exception {
        return readProperties().getProperty("dbAccess");
    }

    public static String getDBHost() throws Exception {
        return readProperties().getProperty("dbHost");
    }
}
