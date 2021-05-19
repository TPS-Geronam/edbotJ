package util;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SharedComRequirements {
    public static boolean checkCenturion(MessageReceivedEvent event) {
        if (!checkSelf(event)) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById(Secrets.CENTURION))) {
                ErrorHandler.CustomEmbedError("You have to be a Centurion to be able to execute this command.", event);
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean checkCuria(MessageReceivedEvent event) {
        if (!checkSelf(event)) {
            if (!event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete().getRoles().contains(event.getGuild().getRoleById(Secrets.CURIA))) {
                ErrorHandler.CustomEmbedError("You have to be a dev to be able to execute this command.", event);
                return true;
            }
            return false;
        }
        return true;
    }

    public static boolean checkSelf(MessageReceivedEvent event) {
        return event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId());
    }
}
