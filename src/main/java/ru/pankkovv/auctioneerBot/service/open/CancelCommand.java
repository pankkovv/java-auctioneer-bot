package ru.pankkovv.auctioneerBot.service.open;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.service.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

import java.util.Comparator;

import static ru.pankkovv.auctioneerBot.model.Auction.bidding;
import static ru.pankkovv.auctioneerBot.model.Auction.lot;

@Slf4j
public class CancelCommand extends Command {

    public CancelCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        bidding.remove(userName);
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName,
                String.format("Ваша ставка отменена. Актуальная информация по лоту name:%s price:%s", lot.getName(), bidding.values().stream().max(Comparator.comparing(Float::valueOf))));


        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }
}
