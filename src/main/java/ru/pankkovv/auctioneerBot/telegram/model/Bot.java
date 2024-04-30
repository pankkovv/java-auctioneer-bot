package ru.pankkovv.auctioneerBot.telegram.model;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.auctioneerBot.telegram.service.admin.RegistrationCommand;
import ru.pankkovv.auctioneerBot.telegram.service.admin.SettingsCommand;
import ru.pankkovv.auctioneerBot.telegram.service.open.HelpCommand;
import ru.pankkovv.auctioneerBot.telegram.service.open.OtherCommand;
import ru.pankkovv.auctioneerBot.telegram.service.open.StartCommand;
import ru.pankkovv.auctioneerBot.telegram.utils.Utils;

/**
 * Собственно, бот
 */
@Slf4j
public final class Bot extends TelegramLongPollingCommandBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private final OtherCommand otherCommand;

    /**
     * Настройки файла для разных пользователей. Ключ - уникальный id чата
     */

    public Bot(String botName, String botToken) {
        super();
        log.debug("Конструктор суперкласса отработал");
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        log.debug("Имя и токен присвоены");

        this.otherCommand = new OtherCommand();
        log.debug("Класс обработки сообщения, не являющегося командой, создан");

        register(new StartCommand("start", "Старт"));
        log.debug("Команда start создана");

        register(new HelpCommand("help", "Помощь"));
        log.debug("Команда help создана");

        register(new SettingsCommand("settings", "Мои настройки"));
        log.debug("Команда settings создана");

        register(new RegistrationCommand("addd", "регистрация админов"));
        log.debug("Команда addd создана");

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
        Long chatId = msg.getChatId();
        String userName = Utils.getUserName(msg);

        String answer = otherCommand.nonCommandExecute(chatId, userName, msg.getText());
        setAnswer(chatId, userName, answer);
    }

    /**
     * Отправка ответа
     *
     * @param chatId   id чата
     * @param userName имя пользователя
     * @param text     текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            log.error(String.format("Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s", e.getMessage(),
                    userName));
            e.printStackTrace();
        }
    }
}