package ru.pankkovv.auctioneerBot.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.pankkovv.auctioneerBot.enums.ExceptionMessage;
import ru.pankkovv.auctioneerBot.exception.AdminNotFoundException;
import ru.pankkovv.auctioneerBot.exception.BetException;
import ru.pankkovv.auctioneerBot.exception.LotNotFoundException;

import static ru.pankkovv.auctioneerBot.model.Auction.*;

public class Utils {
    public static String getUserName(Update update) {
        if (update.hasCallbackQuery()) {
            return (update.getCallbackQuery().getFrom().getUserName() != null) ?
                    update.getCallbackQuery().getFrom().getUserName() :
                    String.format("%s %s", update.getCallbackQuery().getFrom().getLastName(), update.getCallbackQuery().getFrom().getFirstName());
        } else {
            return (update.getMessage().getFrom().getUserName() != null) ? update.getMessage().getFrom().getUserName() :
                    String.format("%s %s", update.getMessage().getFrom().getLastName(), update.getMessage().getFrom().getFirstName());
        }
    }

    public static Long getChatId(Update update) {
        if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        } else {
            return update.getMessage().getChatId();
        }
    }

    public static String getText(Message msg) {
        if (msg.getText() != null) {
            return msg.getText();
        } else if (msg.getCaption() != null) {
            return msg.getCaption();
        } else {
            return "";
        }
    }

    public static void containsAdmin(String username) {
        if (!admin.contains(username)) {
            throw new AdminNotFoundException(ExceptionMessage.NOT_FOUND_RULES_ADMIN_EXCEPTION.label);
        }
    }

    public static void containsLot() {
        if (lot == null) {
            throw new LotNotFoundException(ExceptionMessage.NOT_FOUND_LOT_EXCEPTION.label);
        }
    }

    public static void containsBet(String userName) {
        if (!bidding.containsKey(userName)) {
            throw new BetException(ExceptionMessage.NOT_FOUND_BET_EXCEPTION.label);
        }
    }

    public static void containsBidding() {
        if (bidding.isEmpty()) {
            throw new BetException(ExceptionMessage.NOT_FOUND_BIDDING_EXCEPTION.label);
        }
    }
}
