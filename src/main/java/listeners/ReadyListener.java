package listeners;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;
import util.Secrets;
import util.General;

public class ReadyListener extends ListenerAdapter {
    public void onReady(ReadyEvent event) {
        if (Secrets.debug) {
            Guild g = event.getJDA().getGuildById(Secrets.NODEGUILD);
            Member m = g.retrieveMemberById(Secrets.OWNER).complete();
            User owner = m.getUser();

            owner.openPrivateChannel().queue((channel) -> {
                try { channel.sendMessage(General.getInfoEmbed(event, null, "edbotJ Connected:").build()).queue(); } catch (Exception ignored) { }
            });
        }
    }
}
