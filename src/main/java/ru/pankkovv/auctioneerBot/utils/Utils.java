package ru.pankkovv.auctioneerBot.utils;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import static ru.pankkovv.auctioneerBot.model.Auction.admin;

public class Utils {

    /**
     * Формирование имени пользователя
     *
     * @param msg сообщение
     */
    public static String getUserName(Message msg) {
        return getUserName(msg.getFrom());
    }

    /**
     * Формирование имени пользователя. Если заполнен никнейм, используем его.
     * Если нет - используем фамилию и имя
     *
     * @param user пользователь
     */
    public static String getUserName(User user) {
        return (user.getUserName() != null) ? user.getUserName() :
                String.format("%s %s", user.getLastName(), user.getFirstName());
    }


    /**
     * Авторизация пользователей
     */
    public static boolean containsAdmin(String username) {
        return admin.contains(username);
    }
}
