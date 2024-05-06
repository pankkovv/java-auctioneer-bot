package ru.pankkovv.auctioneerBot.service.admin;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.service.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

import static ru.pankkovv.auctioneerBot.utils.Utils.containsAdmin;

/**
 * Команда, предоставляющая информацию для создания лота
 * доступ только для администратора
 */
@Slf4j
public class CreateLotCommand extends Command {

    public CreateLotCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        if (containsAdmin(userName)) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                    "Для регистрации лота необходимо ввести следующие данные:\n" +
                            "1. Наименование лота\n" +
                            "2. Начальная стоимость\n" +
                            "3. Шаг ставки\n\n" +
                            "Желаю успешных торгов \uD83E\uDD17" +
                            "\n\n чтобы увидеть команды, нажмите /help");
        } else {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                    "Для того чтобы добавить лот в аукцион необходимы права администратора.");
        }

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
