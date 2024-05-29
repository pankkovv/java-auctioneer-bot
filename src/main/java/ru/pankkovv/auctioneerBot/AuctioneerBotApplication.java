package ru.pankkovv.auctioneerBot;


import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.pankkovv.auctioneerBot.model.Bot;

public class AuctioneerBotApplication {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new Bot(
                    "-",
                    "-"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
