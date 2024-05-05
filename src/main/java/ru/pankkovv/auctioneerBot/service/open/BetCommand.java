package ru.pankkovv.auctioneerBot.service.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.exception.BetException;
import ru.pankkovv.auctioneerBot.service.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

@Slf4j
public class BetCommand extends Command {

    public BetCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        try {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                    "❗Чтобы сделать новую ставку напишите мне цену, которую вы готовы предложить за лот. \n" +
                            "Напоминаю, что предложенная цена не может быть меньше актуальной стоимости лота \n" +
                            "или меньше и не кратно установленному шагу торгов.");
        } catch (BetException e) {
            sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                    "Произошла ошибка. Попробуйте еще раз.");
        }

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
