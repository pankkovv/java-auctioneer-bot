package ru.pankkovv.auctioneerBot.telegram.service.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.telegram.service.Command;
import ru.pankkovv.auctioneerBot.telegram.utils.Utils;

/**
 * Команда "Старт"
 */
@Slf4j
public class StartCommand extends Command {

    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Давайте начнём \n\n" +
                        "Я бот-аукционер, рад знакомству! \n" +
                        "Моей задачей является проведение различных аукционов.\n\n" +
                        "Если Вам нужна помощь, нажмите /help");

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}