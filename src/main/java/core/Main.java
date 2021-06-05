package core;

import commands.*;
import commands.mariadb.devs.*;
import commands.mariadb.playtest.*;
import commands.mariadb.projects.*;
import commands.mariadb.hiatuses.*;
import listeners.CommandListener;
import listeners.ReactionAddedListener;
import listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import util.Secrets;

public class Main {
    public static JDABuilder builder;

    public static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] arguments) throws Exception {
        builder = JDABuilder.createDefault(Secrets.getTokenM());
        builder.setToken(Secrets.getTokenM());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setAutoReconnect(true);
        builder.setActivity(Activity.listening(Secrets.prefix + "help | v" + Secrets.VERSION));

        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        BasicConfigurator.configure();
        logger.setLevel(Level.WARN);

        AddListeners();
        AddCommands();

        comVicari.AddVicari();
        comProcuratores.AddProcuratores();

        JDA api = builder.build();
    }

    public static void AddListeners() {
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new ReactionAddedListener());
    }

    public static void AddCommands() {
        Command help = new comHelp();
        Command ahelp = new comAdminHelp();
        Command say = new comSay();
        Command info = new comInfo();
        Command hiatus = new comAddHiatus();
        Command hremove = new comRemoveHiatus();
        Command hupdate = new comUpdateHiatus();
        Command vicari = new comVicari();
        Command procuratores = new comProcuratores();
        Command debug = new comDebug();

        //Command promote = new comPromote();
        //Command demote = new comDemote();
        Command dev = new comAddDev();
        Command rdev = new comRemoveDev();
        Command devall = new comAddAllDevs();
        Command devclear = new comClearDevs();
        Command addproject = new comAddProject();
        Command delproject = new comDeleteProject();
        Command prjadd = new comAddUserToProject();
        Command prjremove = new comRemoveUserFromProject();
        Command rqplay = new comReqReport();
        Command lplay = new comReqAllReports();
        Command rmvplay = new comRemReport();
        Command aplay = new comAddReport();

        //Commands
        //General
        CommandHandler.commands.put("help", help);
        CommandHandler.commands.put("ahelp", ahelp);
        CommandHandler.commands.put("say", say);
        CommandHandler.commands.put("info", info);
        CommandHandler.commands.put("zones", vicari);
        CommandHandler.commands.put("jobs", procuratores);
        CommandHandler.commands.put("debug", debug);

        //MariaDB
        CommandHandler.commands.put("hiatus", hiatus);
        CommandHandler.commands.put("hremove", hremove);
        CommandHandler.commands.put("hupdate", hupdate);
        //CommandHandler.commands.put("promote", promote);
        //CommandHandler.commands.put("demote", demote);
        CommandHandler.commands.put("dev", dev);
        CommandHandler.commands.put("rdev", rdev);
        CommandHandler.commands.put("devall", devall);
        CommandHandler.commands.put("devclear", devclear);
        CommandHandler.commands.put("addproject", addproject);
        CommandHandler.commands.put("delproject", delproject);
        CommandHandler.commands.put("prjadd", prjadd);
        CommandHandler.commands.put("prjremove", prjremove);
        CommandHandler.commands.put("rqplay", rqplay);
        CommandHandler.commands.put("lplay", lplay);
        CommandHandler.commands.put("rmvplay", rmvplay);
        CommandHandler.commands.put("aplay", aplay);

        //Help
        CommandHandler.commandsHelp.add("`general` : Collection of general commands everyone can use." + "\n");
        CommandHandler.commandsHelp.add("`db` : Collection of commands connected to the EoaNB database. Can mostly only be used by devs." + "\n");

        //General
        CommandHandler.commandsHelpGeneral.add("```" + help.help() + "```: " + help.longhelp() + "\n");
        CommandHandler.commandsHelpGeneral.add("```" + info.help() + "```: " + info.longhelp() + "\n");
        CommandHandler.commandsHelpGeneral.add("```" + vicari.help() + "```: " + vicari.longhelp() + "\n");
        CommandHandler.commandsHelpGeneral.add("```" + procuratores.help() + "```: " + procuratores.longhelp() + "\n");

        //MariaDB
        CommandHandler.commandsHelpDB.add("```" + hiatus.help() + "```: " + hiatus.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + hremove.help() + "```: " + hremove.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + hupdate.help() + "```: " + hupdate.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + prjadd.help() + "```: " + prjadd.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + prjremove.help() + "```: " + prjremove.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + rqplay.help() + "```: " + rqplay.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + lplay.help() + "```: " + lplay.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + rmvplay.help() + "```: " + rmvplay.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + aplay.help() + "```: " + aplay.longhelp() + "\n");

        //Admin Help
        CommandHandler.commandsAdminHelp.add("`general` : Collection of general admin commands." + "\n");
        CommandHandler.commandsAdminHelp.add("`db` : Collection of admin commands connected to the EoaNB database." + "\n");

        //General
        CommandHandler.commandsAdminHelpGeneral.add("```" + ahelp.help() + "```: " + ahelp.longhelp() + "\n");
        CommandHandler.commandsAdminHelpGeneral.add("```" + say.help() + "```: " + say.longhelp() + "\n");
        CommandHandler.commandsAdminHelpGeneral.add("```" + debug.help() + "```: " + debug.longhelp() + "\n");

        //MariaDB
        //CommandHandler.commandsAdminHelpDB.add("```" + promote.help() + "```: " + promote.longhelp() + "\n");
        //CommandHandler.commandsAdminHelpDB.add("```" + demote.help() + "```: " + demote.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + dev.help() + "```: " + dev.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + rdev.help() + "```: " + rdev.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + devall.help() + "```: " + devall.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + devclear.help() + "```: " + devclear.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + addproject.help() + "```: " + addproject.longhelp() + "\n");
        CommandHandler.commandsAdminHelpDB.add("```" + delproject.help() + "```: " + delproject.longhelp() + "\n");
    }
}
