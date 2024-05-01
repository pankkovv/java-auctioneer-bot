package ru.pankkovv.auctioneerBot.model;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.auctioneerBot.service.telegram.admin.CreateLotCommand;
import ru.pankkovv.auctioneerBot.service.telegram.admin.RegistrationAdminCommand;
import ru.pankkovv.auctioneerBot.service.telegram.open.HelpCommand;
import ru.pankkovv.auctioneerBot.service.telegram.open.StartCommand;
import ru.pankkovv.auctioneerBot.utils.Utils;

/**
 * Собственно, бот
 */
@Slf4j
public final class Bot extends TelegramLongPollingCommandBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private CreateLotCommand createLotCommand;

    /**
     * Настройки файла для разных пользователей. Ключ - уникальный id чата
     */

    public Bot(String botName, String botToken) {
        super();
        log.debug("Конструктор суперкласса отработал");
        this.BOT_NAME = botName;
        this.BOT_TOKEN = botToken;
        log.debug("Имя и токен присвоены");

        register(new StartCommand("start", "Старт"));
        log.debug("Команда start создана");

        register(new HelpCommand("help", "Помощь"));
        log.debug("Команда help создана");

        register(new RegistrationAdminCommand("regadminkiselbot", "Регистрация администратора"));
        log.debug("Команда registration создана");

        register(new RegistrationAdminCommand("create", "Добавление нового лота"));
        log.debug("Команда create создана");

        register(new RegistrationAdminCommand("viewTable", "Просмотр таблицы торгов"));
        log.debug("Команда view создана");

        register(new RegistrationAdminCommand("setting", "Настройки"));
        log.debug("Команда setting создана");

        register(new RegistrationAdminCommand("bet", "Поднять ставку"));
        log.debug("Команда bet создана");

        register(new RegistrationAdminCommand("cancel", "Отменить ставку"));
        log.debug("Команда cancel создана");

        register(new RegistrationAdminCommand("viewLot", "Просмотр актуального лота"));
        log.debug("Команда view создана");

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

        String answer = createLotCommand.nonCommandExecute(chatId, userName, msg.getText());
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