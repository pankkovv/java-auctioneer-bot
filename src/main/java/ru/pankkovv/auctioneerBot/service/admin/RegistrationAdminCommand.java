package ru.pankkovv.auctioneerBot.service.admin;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.service.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

import static ru.pankkovv.auctioneerBot.model.Auction.admin;

@Slf4j
public class RegistrationAdminCommand extends Command {


    public RegistrationAdminCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        admin.add("eee_kisel");
        admin.add("pankkovv");
        admin.add(userName);

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                String.format("Регистрация прошла успешно (ваш профиль - %s).\n" +
                        "Теперь вам доступны права администратора,", userName));

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
