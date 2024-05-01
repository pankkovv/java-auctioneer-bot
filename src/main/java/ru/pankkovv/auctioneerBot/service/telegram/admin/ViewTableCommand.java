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
public class ViewTableCommand extends Command {
    public AdminService service;
    protected ViewTableCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);
        Admin admin = service.getUser(userName);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
                this.getCommandIdentifier()));

        Long chatId = chat.getId();
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                "Команда предоставляет доступ к полной таблице торгов для администратора бота.");

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
                this.getCommandIdentifier()));

    }
}
