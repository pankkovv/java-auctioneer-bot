package ru.pankkovv.auctioneerBot.service.telegram.admin;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.model.Admin;
import ru.pankkovv.auctioneerBot.service.auctioneer.admin.AdminService;
import ru.pankkovv.auctioneerBot.service.telegram.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

@Slf4j
public class RegistrationAdminCommand extends Command {
    public AdminService service;

    public RegistrationAdminCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        Long chatId = chat.getId();
        Admin admin = service.create(userName);
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                String.format("Регистрация прошла успешно (ваш профиль - %s).\n" +
                                "Теперь вам доступны права администратора."
                        , admin.toString()));

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
