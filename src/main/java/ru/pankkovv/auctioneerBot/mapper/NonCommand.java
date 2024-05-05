package ru.pankkovv.auctioneerBot.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.pankkovv.auctioneerBot.exception.BetException;
import ru.pankkovv.auctioneerBot.model.Auction;
import ru.pankkovv.auctioneerBot.model.Lot;

import static ru.pankkovv.auctioneerBot.model.Auction.bidding;
import static ru.pankkovv.auctioneerBot.model.Auction.lot;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
@Slf4j
public class NonCommand {
    public String nonCommandExecute(Long chatId, String userName, String text) {
        log.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой",
                userName, text));

        String answer;
        text = text.replaceAll("-", "")//избавляемся от отрицательных чисел (умники найдутся)
                .replaceAll(", ", ",")//меняем ошибочный разделитель "запятая+пробел" на запятую
                .replaceAll(" ", ",");//меняем разделитель-пробел на запятую

        String[] parameters = text.split(",");

        try {
            switch (parameters.length) {
                case 1:
                    log.debug(String.format("Пользователь %s. Пробуем обновить цену лота из сообщения \"%s\"", userName, text));

                    Float bet = createBet(parameters);
                    bidding.put(userName, bet);
                    answer = String.format("Предложение по лоту обновлено. Вы всегда можете их посмотреть с помощью /view_bet %s", bidding.get(userName));
                    break;

                case 3:
                    log.debug(String.format("Пользователь %s. Пробуем создать объект из сообщения \"%s\"", userName, text));

                    Lot lot = createLot(parameters);
                    Auction.lot = lot;
                    answer = String.format("Лот создан: %s.\n" +
                            "Вы всегда можете посмотреть его с помощью /view_lot", lot);
                    break;

                default:
                    throw new IllegalArgumentException(String.format("Не удалось разбить сообщение \"%s\" на нужное количество составляющих",
                            text));
            }


        } catch (BetException e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект из сообщения \"%s\". %s", userName, text, e.getMessage()));

            answer = e.getMessage() + "❗Изменения не были применены.";
        } catch (Exception e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект из сообщения \"%s\". %s. %s", userName, text, e.getClass().getSimpleName(), e.getMessage()));

            answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.\n" +
                    "Возможно, Вам поможет /help";
        }

        log.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой", userName, text));

        return answer;
    }

    private Lot createLot(String[] parameters) {
        String name = parameters[0];
        Float startPrice = Float.parseFloat(parameters[1]);
        Float step = Float.parseFloat(parameters[2]);

        return Lot.builder().name(name).startPrice(startPrice).step(step).build();
    }

    private Float createBet(String[] parameters) {
        Float bet = Float.parseFloat(parameters[0]);
        Float maxPrice = 0.0F;

        for (String name : bidding.keySet()) {
            if (bidding.get(name) > maxPrice) {
                maxPrice = bidding.get(name);
            }
        }

        if (bet <= lot.getStartPrice() ||
                bet.equals(maxPrice) ||
                (bet - maxPrice) < lot.getStep() ||
                (bet - maxPrice) % lot.getStep() != 0) {
            throw new BetException();
        }

        return bet;
    }

}