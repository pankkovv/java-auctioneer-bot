package ru.pankkovv.auctioneerBot.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.pankkovv.auctioneerBot.exception.BetException;
import ru.pankkovv.auctioneerBot.model.Auction;
import ru.pankkovv.auctioneerBot.model.Lot;

import java.io.FileWriter;
import java.time.LocalDateTime;

import static ru.pankkovv.auctioneerBot.model.Auction.*;
import static ru.pankkovv.auctioneerBot.utils.Utils.containsAdmin;

/**
 * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
 */
@Slf4j
public class NonCommand {
    public String nonCommandExecute(Long chatId, String userName, String text) {
        log.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой", userName, text));

        String answer;
        text = text.replaceAll("-", "")//избавляемся от отрицательных чисел (умники найдутся)
                .replaceAll(", ", ",")//меняем ошибочный разделитель "запятая+пробел" на запятую
                .replaceAll(" ", ",");//меняем разделитель-пробел на запятую

        String[] parameters = text.split(",");

        try (FileWriter fileWriter = new FileWriter("bidding.csv", true)) {
            switch (parameters.length) {
                case 1:
                    if (containsAdmin(userName)) {
                        log.debug(String.format("Пользователь %s. Пробуем обновить цену лота из сообщения \"%s\"", userName, text));

                        if (flag) {
                            fileWriter.append("time;username;bet\n");
                            flag = false;
                        }

                        Float bet = createBet(parameters);
                        bidding.put(userName, bet);
                        lot.setCurrentPrice(bet);
                        fileWriter.append(String.format("%s;%s;%s\n", LocalDateTime.now(), userName, bet));

                        answer = String.format("Ваша ставка принята: %s.\n\n" +
                                "Посмотреть актуальную информацию по лоту с помощью /view_lot", lot.getCurrentPrice());
                    } else {
                        answer = "Для того чтобы добавить лот в аукцион необходимы права администратора.";
                    }
                    break;

                case 3:
                    if (lot != null) {
                        log.debug(String.format("Пользователь %s. Пробуем создать объект из сообщения \"%s\"", userName, text));

                        Lot lot = createLot(parameters);
                        Auction.lot = lot;
                        answer = String.format("Лот создан: %s.\n\n" +
                                "Посмотреть актуальную информацию по лоту с помощью /view_lot", lot);
                    } else {
                        answer = "Торги еще не начались. Пожалуйста, дождитесь регистрации лота.";
                    }
                    break;

                default:
                    answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.\n\n" +
                            "Возможно, Вам поможет /help";
            }


        } catch (BetException e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект из сообщения \"%s\". %s", userName, text, e.getMessage()));

            answer = "Изменения не были применены \uD83D\uDE14 \n\n" + e.getMessage();
        } catch (Exception e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект из сообщения \"%s\". %s. %s", userName, text, e.getClass().getSimpleName(), e.getMessage()));

            answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.\n\n" +
                    "Возможно, Вам поможет /help";
        }

        log.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой", userName, text));

        return answer;
    }

    /**
     * Функция создания нового лота из переданного сообщения
     */
    private Lot createLot(String[] parameters) {
        String name = parameters[0];
        Float startPrice = Float.parseFloat(parameters[1]);
        Float step = Float.parseFloat(parameters[2]);

        return Lot.builder().name(name).startPrice(startPrice).currentPrice(startPrice).step(step).build();
    }

    /**
     * Функция обработки предложенной ставки
     */
    private Float createBet(String[] parameters) {
        Float bet = Float.parseFloat(parameters[0]);
        Float maxPrice = 0.0F;

        for (String name : bidding.keySet()) {
            if (bidding.get(name) > maxPrice) {
                maxPrice = bidding.get(name);
            }
        }

        if (bet <= lot.getCurrentPrice() ||
                bet.equals(maxPrice) ||
                (bet - maxPrice) < lot.getStep() ||
                (bet - maxPrice) % lot.getStep() != 0) {
            throw new BetException("Напомню, если вы хотите предложить свою цену за лот, необходимо выполнить следующие условия:\n" +
                    "1. Ваша цена не может быть меньше актуальной стоимости лота \n" +
                    "2. Увеличение цены лота не может быть меньше установленного шага торгов \n" +
                    "3. Увеличение цены лота должно быть кратно установленному шагу торгов\n\n" +
                    "Попробуйте еще раз \uD83D\uDE0A");
        }

        return bet;
    }

}