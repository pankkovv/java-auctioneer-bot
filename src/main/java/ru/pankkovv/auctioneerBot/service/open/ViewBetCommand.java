package ru.pankkovv.auctioneerBot.service.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.service.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

import java.util.Comparator;
import java.util.stream.Stream;

import static ru.pankkovv.auctioneerBot.model.Auction.bidding;
import static ru.pankkovv.auctioneerBot.model.Auction.lot;

@Slf4j
public class ViewBetCommand extends Command {

    public ViewBetCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                String.format("Актуальная ставка: %s ", bidding.values().stream().max(Comparator.comparing(Float::valueOf)).orElse(null)));

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
