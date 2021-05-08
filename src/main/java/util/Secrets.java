package util;

import commands.mariadb.projects.ProjectAddRequest;
import commands.mariadb.projects.ProjectRemoveRequest;
import org.apache.commons.lang3.SystemUtils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class Secrets {
    public static String nodeGuildId = "318668421719916545"; //W:286194821394071552 E:318668421719916545
    public static String ownerId = "212693204519223296";
    public static long startTime = System.nanoTime();
    public static String prefix = "ed!";
    public static boolean debug = false;
    public static String version = "1.1.0";
    public static HashMap<String, String> vicari = new HashMap<String, String>();
    public static HashMap<String, String> procuratores = new HashMap<String, String>();
    public static String adminChan = "532399694442266656";

    //Hiatus
    //public static HashMap<String, HiatusRequest> hiatusList = new HashMap<String, HiatusRequest>();
    public static String hiatusEndchan = adminChan;

    //Projects
    public static HashMap<String, ProjectAddRequest> projectReqList = new HashMap<String, ProjectAddRequest>();
    public static HashMap<String, ProjectRemoveRequest> projectRemList = new HashMap<String, ProjectRemoveRequest>();
    public static String projectEndchan = adminChan;

    private static Properties readProperties() throws Exception {
        Properties properties = new Properties();
        FileInputStream fis;
        if (SystemUtils.IS_OS_LINUX) {
            fis = new FileInputStream("/opt/edbotj_dbot/prop.xml");
        }
        else {
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
