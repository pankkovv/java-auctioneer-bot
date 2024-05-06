package ru.pankkovv.auctioneerBot;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.pankkovv.auctioneerBot.model.Bot;

import java.util.Map;

public class AuctioneerBotApplication {
    private static final Map<String, String> getenv = System.getenv();

    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(
                    "eee_kisel_bot",
                    "7016070458:AAHrtTP7_bv0hnXFY0dPK4ncU098x8fcnvw"));


        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
