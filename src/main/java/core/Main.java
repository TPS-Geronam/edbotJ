package core;

import commands.*;
import commands.mariadb.devs.*;
import commands.mariadb.projects.*;
import commands.mariadb.hiatuses.*;
import listeners.CommandListener;
import listeners.ReactionAddedListener;
import listeners.ReadyListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.JDA;
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

    static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] arguments) throws Exception {
        builder = JDABuilder.createDefault(Secrets.getTokenM());
        builder.setToken(Secrets.getTokenM());
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setAutoReconnect(true);
        builder.setActivity(Activity.listening(Secrets.prefix + "help | v" + Secrets.version));

        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        BasicConfigurator.configure();
        logger.setLevel(Level.WARN);

        AddListeners();
        AddCommands();
        AddVicari();
        AddProcuratores();

        JDA api = builder.build();
    }

    public static void AddListeners() {
        builder.addEventListeners(new ReadyListener());
        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new ReactionAddedListener());
    }

    public static void AddVicari() {
        Secrets.vicari.put("a", "721305581805240410");
        Secrets.vicari.put("b", "608041250951528507");
        Secrets.vicari.put("c", "721305674541170818");
        Secrets.vicari.put("d", "608032654394261547");
        Secrets.vicari.put("machina", "478479243991318536");
        Secrets.vicari.put("artifex", "321670679743430657");
    }

    public static void AddProcuratores() {
        Secrets.procuratores.put("programmator", "319861468705325057");
        Secrets.procuratores.put("historicus", "319861531879800832");
    }

    public static void AddCommands() {
        Command help = new comHelp();
        Command ahelp = new comAdminHelp();
        Command say = new comSay();
        Command info = new comInfo();
        Command hiatus = new comAddHiatus();
        Command hremove = new comRemoveHiatus();
        Command hupdate = new comUpdateHiatus();
        //Command vicari = new comVicari();
        //Command procuratores = new comProcuratores();
        Command debug = new comDebug();

        //Command promote = new comPromote();
        //Command demote = new comDemote();
        Command dev = new comAddDev();
        Command rdev = new comRemoveDev();
        Command devall = new comAddAllDevs();
        Command devclear = new comClearDevs();
        //Command addproject = new comAddProject();
        //Command delproject = new comDeleteProject();
        //Command prjadd = new comAddUserToProject();
        //Command prjremove = new comRemoveUserFromProject();

        //Commands
        //General
        CommandHandler.commands.put("help", help);
        CommandHandler.commands.put("ahelp", ahelp);
        CommandHandler.commands.put("say", say);
        CommandHandler.commands.put("info", info);
        //CommandHandler.commands.put("vicari", vicari);
        //CommandHandler.commands.put("procuratores", procuratores);
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
        //CommandHandler.commands.put("addproject", addproject);
        //CommandHandler.commands.put("delproject", delproject);
        //CommandHandler.commands.put("prjadd", prjadd);
        //CommandHandler.commands.put("prjremove", prjremove);

        //Help
        CommandHandler.commandsHelp.add("`general` : Collection of general commands everyone can use." + "\n");
        CommandHandler.commandsHelp.add("`db` : Collection of commands connected to the EoaNB database. Can mostly only be used by devs." + "\n");

        //General
        CommandHandler.commandsHelpGeneral.add("```" + help.help() + "```: " + help.longhelp() + "\n");
        CommandHandler.commandsHelpGeneral.add("```" + info.help() + "```: " + info.longhelp() + "\n");
        //CommandHandler.commandsHelpGeneral.add("```" + vicari.help() + "```: " + vicari.longhelp() + "\n");
        //CommandHandler.commandsHelpGeneral.add("```" + procuratores.help() + "```: " + procuratores.longhelp() + "\n");

        //MariaDB
        CommandHandler.commandsHelpDB.add("```" + hiatus.help() + "```: " + hiatus.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + hremove.help() + "```: " + hremove.longhelp() + "\n");
        CommandHandler.commandsHelpDB.add("```" + hupdate.help() + "```: " + hupdate.longhelp() + "\n");
        //CommandHandler.commandsHelpDB.add("```" + prjadd.help() + "```: " + prjadd.longhelp() + "\n");
        //CommandHandler.commandsHelpDB.add("```" + prjremove.help() + "```: " + prjremove.longhelp() + "\n");

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
        //CommandHandler.commandsAdminHelpDB.add("```" + addproject.help() + "```: " + addproject.longhelp() + "\n");
        //CommandHandler.commandsAdminHelpDB.add("```" + delproject.help() + "```: " + delproject.longhelp() + "\n");
    }
}
