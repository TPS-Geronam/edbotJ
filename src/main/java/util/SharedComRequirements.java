package util;

import core.ErrorHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class SharedComRequirements {
    public static boolean checkCenturion(MessageReceivedEvent event) {
        return checkId(event, Secrets.CENTURION, true, "You have to be a dev to be able to execute this command.");
    }

    public static boolean checkCuria(MessageReceivedEvent event) {
        return checkId(event, Secrets.CURIA, true, "You have to be a dev to be able to execute this command.");
    }

    public static boolean checkOwner(MessageReceivedEvent event) {
        return checkId(event, Secrets.OWNER, false, "You have to be the owner to be able to execute this command.");
    }

    public static boolean checkId(MessageReceivedEvent event, String id, boolean idIsRole, String msg) {
        if (checkSelf(event)) {
            return true;
        }

        if (idIsRole && !event.getGuild().retrieveMemberById(event.getAuthor().getId()).complete()
            .getRoles().contains(event.getGuild().getRoleById(id))) {
            ErrorHandler.CustomEmbedError(msg, event);
            return true;
        }

        if (!event.getMessage().getAuthor().getId().equals(id)) {
            ErrorHandler.CustomEmbedError(msg, event);
            return true;
        }

        return false;
    }

    public static boolean checkSelf(MessageReceivedEvent event) {
        return event.getMessage().getAuthor().getId().equals(event.getJDA().getSelfUser().getId());
    }
}
