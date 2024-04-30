package ru.pankkovv.auctioneerBot.telegram.service.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.telegram.service.Command;
import ru.pankkovv.auctioneerBot.telegram.utils.Utils;

@Slf4j
public class BetCommand extends Command {

    protected BetCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                "Спешу напомнить, что у меня есть следующие функции:\n" +
                        "/up\n" +
                        "/cancel\n" +
                        "/view\n\n" +
                        "Желаю удачи\uD83D\uDE42");


        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
