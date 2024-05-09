package ru.pankkovv.auctioneerBot.mapper;

import lombok.extern.slf4j.Slf4j;
import ru.pankkovv.auctioneerBot.exception.BetException;
import ru.pankkovv.auctioneerBot.model.Auction;
import ru.pankkovv.auctioneerBot.model.Lot;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Comparator;

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
        text = text.replaceAll("-", "")//избавляемся от отрицательных чисел
                .replaceAll("/", "")//избавляемся от slash-разделителя
                .replaceAll(", ", ",")//меняем ошибочный разделитель "запятая+пробел" на запятую
                .replaceAll(" ", ",");//меняем разделитель-пробел на запятую

        String[] parameters = text.split(",");

        switch (parameters.length) {
            case 1:
                switch (text) {
                    case "start":
                        answer = "Давайте начнём \n\n" +
                                "Я бот-аукционер, рад знакомству! \n" +
                                "Моей задачей является проведение различных аукционов.\n\n" +
                                "Если вам нужна помощь, нажмите /help\n\n" +
                                "Основные функции бота:\n" +
                                "/registration - регистрация админа\n" +
                                "/create - создание лота\n" +
                                "/view_table- просмотр таблицы торгов\n" +
                                "/bet - поднять ставку\n" +
                                "/cancel - отменить ставку\n" +
                                "/view_lot - просмотр актуальной информации торгуемого лота\n\n" +
                                "\n\nчтобы увидеть команды, нажмите /help";

                        break;

                    case "help":
                        answer = "Спешу напомнить, что у меня есть следующие функции:\n" +
                                "/registration - регистрация админа\n" +
                                "/create - создание лота\n" +
                                "/view_table- просмотр таблицы торгов\n" +
                                "/bet - поднять ставку\n" +
                                "/cancel - отменить ставку\n" +
                                "/view_lot - просмотр актуальной информации торгуемого лота\n\n" +
                                "Желаю удачи\uD83D\uDE42";
                        break;

                    case "registration":
                        admin.add(userName);

                        answer = String.format("Регистрация прошла успешно, рад знакомству, %s \uD83E\uDD1D \n" +
                                "Теперь вам доступны права администратора." +
                                "\n\nчтобы увидеть команды, нажмите /help", userName);
                        break;

                    case "create":
                        if (containsAdmin(userName)) {
                            answer = "Для регистрации лота необходимо ввести следующие данные:\n" +
                                    "1. Наименование лота\n" +
                                    "2. Начальная стоимость\n" +
                                    "3. Шаг ставки\n\n" +
                                    "Желаю успешных торгов \uD83E\uDD17" +
                                    "\n\nчтобы увидеть команды, нажмите /help";
                        } else {
                            answer = "Для того чтобы добавить лот в аукцион необходимы права администратора.";
                        }
                        break;

                    case "view_table":
                        if (containsAdmin(userName)) {
                            answer = String.format("username bet\n" +
                                    "%s", bidding);
                        } else {
                            answer = "Для просмотра таблицы торгов необходимы права администратора.";
                        }
                        break;

                    case "bet":
                        answer = "❗Чтобы сделать новую ставку напишите мне цену, которую вы готовы предложить за лот. \n" +
                                "Напоминаю, что предложенная цена не может быть меньше актуальной стоимости лота \n" +
                                "или меньше и не кратно установленному шагу торгов.❗" +
                                "\n\nчтобы увидеть команды, нажмите /help";
                        break;

                    case "cancel":
                        if (lot != null) {
                            bidding.remove(userName);
                            lot.setCurrentPrice(bidding.values().stream().max(Comparator.comparing(Float::valueOf)).orElse(lot.getStartPrice()));
                            answer = String.format("Ваша ставка отменена. Актуальная информация по лоту: %s" +
                                    "\n\nчтобы увидеть команды, нажмите /help", lot);
                        } else {
                            answer = "Торги еще не начались. Пожалуйста, дождитесь регистрации лота.";
                        }

                        break;

                    case "view_lot":
                        answer = String.format("Актуальная информация по лоту: %s" +
                                "\n\nчтобы увидеть команды, нажмите /help", lot);

                        break;

                    default:
                        try (FileWriter fileWriter = new FileWriter("bidding.csv", true)) {
                            Float bet = createBet(parameters);

                            if (lot != null) {
                                log.debug(String.format("Пользователь %s. Пробуем обновить цену лота из сообщения \"%s\"", userName, text));

                                if (flag) {
                                    fileWriter.append("time;username;bet\n");
                                    flag = false;
                                }

                                bidding.put(userName, bet);
                                lot.setCurrentPrice(bet);
                                fileWriter.append(String.format("%s;%s;%s\n", LocalDateTime.now(), userName, bet));

                                answer = String.format("Ваша ставка принята: %s.\n\n" +
                                        "Посмотреть актуальную информацию по лоту с помощью /view_lot", lot.getCurrentPrice());

                            } else {
                                answer = "Торги еще не начались. Пожалуйста, дождитесь регистрации лота.";
                            }

                        } catch (BetException e) {
                            answer = "Изменения не были применены \uD83D\uDE14 \n\n" + e.getMessage();
                        } catch (Exception e) {
                            answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату.\n\n" +
                                    "Возможно, Вам поможет /help";
                        }
                }
                break;

            case 3:
                if (containsAdmin(userName)) {
                    log.debug(String.format("Пользователь %s. Пробуем создать объект из сообщения \"%s\"", userName, text));

                    Lot lot = createLot(parameters);
                    Auction.lot = lot;
                    answer = String.format("Лот создан: %s.\n\n" +
                            "Посмотреть актуальную информацию по лоту с помощью /view_lot", lot);
                } else {
                    answer = "Для того чтобы добавить лот в аукцион необходимы права администратора.";
                }
                break;

            default:
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