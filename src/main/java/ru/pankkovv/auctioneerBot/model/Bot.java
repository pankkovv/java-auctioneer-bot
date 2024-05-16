package ru.pankkovv.auctioneerBot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.auctioneerBot.enums.ExceptionMessage;
import ru.pankkovv.auctioneerBot.service.NonCommandService;
import ru.pankkovv.auctioneerBot.utils.Utils;

import java.io.File;
import java.util.List;

import static ru.pankkovv.auctioneerBot.model.Auction.admin;
import static ru.pankkovv.auctioneerBot.model.Auction.lot;

/**
 * Бот
 */
@Slf4j
public final class Bot extends TelegramLongPollingCommandBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private final NonCommandService nonCommandService;
    @Getter
    @Setter
    private Auction auction;

    /**
     * Настройки файла для разных пользователей. Ключ - уникальный id чата
     */

    public Bot(String botName, String botToken) {
        super();
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        this.nonCommandService = new NonCommandService();

//        admin.add("pankkovv");
        lot = Lot.builder()
                .name("ring")
                .startPrice(100.0F)
                .currentPrice(100.0F)
                .step(50.0F)
                .photo(new File("lots/lot.png"))
                .build();
        log.info("Бот создан!");
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    /**
     * Ответ на запрос, не являющийся командой
     */
    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        String userName = Utils.getUserName(update);
        Long chatId = Utils.getChatId(update);

        //кнопка
        //команда, ставка, данные о лоте - строка
        //данные лота + фото, фото - файл с описанием

        try {
            if (update.hasCallbackQuery()) {
                Object answer = nonCommandService.nonCommandExecute(chatId, userName, update.getCallbackQuery());

                if (answer.getClass().equals(SendPhoto.class)) {
                    execute((SendPhoto) answer);
                } else {
                    execute((SendMessage) answer);
                }

            } else if (msg.getText() != null) {
                SendPhoto answer = nonCommandService.nonCommandExecute(chatId, userName, Utils.getText(msg));
                execute(answer);

            } else if (msg.getPhoto() != null) {
                List<PhotoSize> photos = msg.getPhoto();
                GetFile getFile = new GetFile(photos.get(photos.size() - 1).getFileId());
                org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
                File photo = downloadFile(file, new File("lots/lot.png"));

                SendPhoto answer = nonCommandService.nonCommandExecute(chatId, userName, Utils.getText(msg), photo);
                execute(answer);

            } else {
                SendMessage answer = new SendMessage();
                answer.setText(ExceptionMessage.NOT_FOUND_COMMAND_EXCEPTION.label);
                answer.setChatId(chatId.toString());
                execute(answer);
            }

        } catch (TelegramApiException e) {
            log.error(String.format("Ошибка %s. Пользователь: %s", e.getMessage(), userName));
            e.printStackTrace();
        }
    }
}