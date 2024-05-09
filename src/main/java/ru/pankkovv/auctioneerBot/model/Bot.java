package ru.pankkovv.auctioneerBot.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.pankkovv.auctioneerBot.mapper.NonCommand;
import ru.pankkovv.auctioneerBot.service.admin.CreateLotCommand;
import ru.pankkovv.auctioneerBot.service.admin.RegistrationAdminCommand;
import ru.pankkovv.auctioneerBot.service.admin.ViewTableCommand;
import ru.pankkovv.auctioneerBot.service.open.*;
import ru.pankkovv.auctioneerBot.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Бот
 */
@Slf4j
public final class Bot extends TelegramLongPollingCommandBot {

    private final String BOT_NAME;
    private final String BOT_TOKEN;

    private final NonCommand nonCommand;
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
        this.nonCommand = new NonCommand();

        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("start", "Старт"));
        listOfCommands.add(new BotCommand("help", "Помощь"));
        listOfCommands.add(new BotCommand("registration", "Регистрация администратора"));
        listOfCommands.add(new BotCommand("create", "Добавление нового лота"));
        listOfCommands.add(new BotCommand("view_table", "Просмотр таблицы торгов"));
        listOfCommands.add(new BotCommand("bet", "Поднять ставку"));
        listOfCommands.add(new BotCommand("cancel", "Отменить ставку"));
        listOfCommands.add(new BotCommand("view_lot", "Просмотр выставленного лота"));

        try{
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

//                register(new StartCommand("start", "Старт"));
//        log.debug("Команда start создана");
//
//        register(new HelpCommand("help", "Помощь"));
//        log.debug("Команда help создана");
//
//        register(new RegistrationAdminCommand("registration", "Регистрация администратора"));
//        log.debug("Команда registration создана");
//
//        register(new CreateLotCommand("create", "Добавление нового лота"));
//        log.debug("Команда create создана");
//
//        register(new ViewTableCommand("view_table", "Просмотр таблицы торгов"));
//        log.debug("Команда viewTable создана");
//
//        register(new BetCommand("bet", "Поднять ставку"));
//        log.debug("Команда bet создана");
//
//        register(new CancelCommand("cancel", "Отменить ставку"));
//        log.debug("Команда cancel создана");
//
//        register(new ViewLotCommand("view_lot", "Просмотр выставленного лота"));
//        log.debug("Команда viewLot создана");

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

        String answer = nonCommand.nonCommandExecute(chatId, userName, msg.getText());
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