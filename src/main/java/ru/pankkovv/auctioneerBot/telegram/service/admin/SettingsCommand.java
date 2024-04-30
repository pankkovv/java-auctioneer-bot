package ru.pankkovv.auctioneerBot.telegram.service.admin;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.telegram.nonCommand.Settings;
import ru.pankkovv.auctioneerBot.telegram.model.Bot;
import ru.pankkovv.auctioneerBot.telegram.service.Command;
import ru.pankkovv.auctioneerBot.telegram.utils.Utils;


/**
 * Команда получения текущих настроек
 */
@Slf4j
public class SettingsCommand extends Command {

    public SettingsCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName,
                this.getCommandIdentifier()));

        Long chatId = chat.getId();
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                "*Текущие настройки*\n" +
                                "- минимальное число, использующееся в заданиях - *%s*\n" +
                                "- максимальное число, использующееся в заданиях - *%s*\n" +
                                "- число страниц итогового файла - *%s*\n\n" +
                                "Если Вы хотите изменить эти параметры, введите через пробел или запятую 3 числа - " +
                                "минимальное число, максимальное число и количество страниц в файле (не более 10)\n\n" +
                                "\uD83D\uDC49 Например, 3,15,6 или 4 17 3");

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName,
                this.getCommandIdentifier()));
    }
}