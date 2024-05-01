package ru.pankkovv.auctioneerBot.service.telegram.admin;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.pankkovv.auctioneerBot.model.Lot;
import ru.pankkovv.auctioneerBot.model.Admin;
import ru.pankkovv.auctioneerBot.service.auctioneer.admin.AdminService;
import ru.pankkovv.auctioneerBot.exception.IllegalSettingsException;
import ru.pankkovv.auctioneerBot.service.auctioneer.lot.LotService;
import ru.pankkovv.auctioneerBot.service.telegram.Command;
import ru.pankkovv.auctioneerBot.utils.Utils;

@Slf4j
public class CreateLotCommand extends Command {
    public AdminService adminService;
    public LotService lotService;

    public CreateLotCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] arguments) {
        String userName = Utils.getUserName(user);
        Admin admin = adminService.getUser(userName);

        log.debug(String.format("Пользователь %s. Начато выполнение команды %s", userName, this.getCommandIdentifier()));

        Long chatId = chat.getId();
        sendAnswer(absSender, chatId, this.getCommandIdentifier(), userName,
                "Необходимо зарегистрировать лот, который вы хотите разыграть.\n" +
                        "Для этого необходимо ввести следующие данные:\n" +
                        "1.Наименование лота\n" +
                        "2.Минимальная ставка\n" +
                        "3.Шаг торгов\n");

        log.debug(String.format("Пользователь %s. Завершено выполнение команды %s", userName, this.getCommandIdentifier()));
    }

    /**
     * Обработка сообщения, не являющегося командой (т.е. обычного текста не начинающегося с "/")
     */

    public String nonCommandExecute(Long chatId, String userName, String text) {
        log.debug(String.format("Пользователь %s. Начата обработка сообщения \"%s\", не являющегося командой", userName, text));

        String answer;
        try {
            log.debug(String.format("Пользователь %s. Пробуем создать объект из сообщения \"%s\"", userName, text));

            Lot newLot = lotService.create(mapToLot(text));

            log.debug(String.format("Пользователь %s. Объект из сообщения \"%s\" создан и сохранён", userName, text));

            answer = String.format("Лот создан. Вы всегда можете его посмотреть с помощью /viewLot %s", newLot.toString());
        } catch (IllegalSettingsException e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект из сообщения \"%s\" %s", userName, text, e.getMessage()));

            answer = e.getMessage() + "\n\n❗ Объект не сохранен. Вы можете попробовать еще раз.";
        } catch (Exception e) {
            log.debug(String.format("Пользователь %s. Не удалось создать объект настроек из сообщения \"%s\". %s. %s", userName, text, e.getClass().getSimpleName(), e.getMessage()));

            answer = "Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату, или " +
                    "использовали слишком большие числа\n\n" +
                    "Возможно, Вам поможет /help";
        }

        log.debug(String.format("Пользователь %s. Завершена обработка сообщения \"%s\", не являющегося командой", userName, text));

        return answer;
    }

    /**
     * Создание настроек из полученного пользователем сообщения
     *
     * @param text текст сообщения
     * @throws IllegalArgumentException пробрасывается, если сообщение пользователя не соответствует формату
     */
    private Lot mapToLot(String text) throws IllegalArgumentException {
        //отсекаем файлы, стикеры, гифки и прочий мусор
        if (text == null) {
            throw new IllegalArgumentException("Сообщение не является текстом");
        }
        text = text.replaceAll("-", "")//избавляемся от отрицательных чисел (умники найдутся)
                .replaceAll(", ", ",")//меняем ошибочный разделитель "запятая+пробел" на запятую
                .replaceAll(" ", ",");//меняем разделитель-пробел на запятую
        String[] parameters = text.split(",");
        if (parameters.length != 3) {
            throw new IllegalArgumentException(String.format("Не удалось разбить сообщение \"%s\" на 3 составляющих",
                    text));
        }
        String name = parameters[0];
        Float minPrice = Float.parseFloat(parameters[1]);
        Float step = Float.parseFloat(parameters[2]);

        return Lot.builder().name(name).minPrice(minPrice).step(step).build();
    }

    /**
     * Валидация настроек
     */
    private void validateSettings(int min, int max, int listCount) {
        if (min == 0 || max == 0 || listCount == 0) {
            throw new IllegalSettingsException("\uD83D\uDCA9 Ни один из параметров не может равняться 0. Поражён " +
                    "Вашей неудачей");
        }
        if (min > 10000 || max > 10000) {
            throw new IllegalSettingsException("\uD83D\uDCA9 Заданные значения слишком велики. Не забывайте, мы учим " +
                    "детей устному счёту, а не рассчитываем траекторию полёта к Юпитеру");
        }
    }

    /**
     * Добавление настроек пользователя в мапу, чтобы потом их использовать для этого пользователя при генерации файла
     * Если настройки совпадают с дефолтными, они не сохраняются, чтобы впустую не раздувать мапу
     * @param chatId   id чата
     * @param settings настройки
     */
//    private void saveUserSettings(Long chatId, Settings settings) {
//        if (!settings.equals(Bot.getDefaultSettings())) {
//            Bot.getUserSettings().put(chatId, settings);
//        } else {
//            Bot.getUserSettings().remove(chatId);
//        }
//    }

    /**
     * Формирование оповещения о некорректных настройках
     */
//    private String createSettingWarning(Settings settings) {
//        return (settings.getPlusMinusUniqueTaskCount() == 0) ?
//                String.format("\r\n\r\n\uD83D\uDCA9 Для пары чисел %s - %s не существует сложений и вычитаний, " +
//                        "результат которых попадает в интервал между ними, поэтому вместо минимального значения при " +
//                        "формировании заданий будет использовано число 1", settings.getMin(), settings.getMax()) :
//                "";
//    }
}
