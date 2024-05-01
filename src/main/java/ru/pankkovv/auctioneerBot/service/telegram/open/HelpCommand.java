package ru.pankkovv.auctioneerBot.service.telegram.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.service.telegram.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

/**
 * Команда "Помощь"
 */
@Slf4j
public class HelpCommand extends Command {

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                        "Спешу напомнить, что у меня есть следующие функции:\n" +
                                "/bet - поднять ставку\n" +
                                "/cancel - отменить ставку\n" +
                                "/view - просмотр актуальной информации торгуемого лота\n\n" +
                                "Желаю удачи\uD83D\uDE42");


        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}