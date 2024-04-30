package ru.pankkovv.auctioneerBot.telegram.service.admin;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.telegram.model.Bot;
import ru.pankkovv.auctioneerBot.telegram.nonCommand.Settings;
import ru.pankkovv.auctioneerBot.telegram.service.Command;
import ru.pankkovv.auctioneerBot.telegram.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RegistrationCommand extends Command {
    private List<String> admin = new ArrayList<>();

    public RegistrationCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);
        admin.add(userName);
        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
                this.getCommandIdentifier()));

        Long chatId = chat.getId();
        Settings settings = Bot.getUserSettings(chatId);
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                String.format("*Админы*\n" +
                                "*%s*\n",
                        admin.toArray()));

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
                this.getCommandIdentifier()));
    }
}
