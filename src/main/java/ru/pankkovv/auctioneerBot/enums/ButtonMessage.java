package ru.pankkovv.auctioneerBot.enums;

public enum ButtonMessage {
    START("Главное меню \uD83D\uDC8C"),
    HELP("Помощь \uD83E\uDD7A"),
    LOT("Посмотреть лот \uD83D\uDC8D"),
    CREATE_BET("Сделать ставку \uD83D\uDCB0"),
    GET_BET("Посмотреть свою ставку \uD83D\uDC40"),
    CANCEL("Отменить ставку ❌"),
    REGISTRATION("Регистрация \uD83E\uDD1D"),
    CREATE("Создать лот \uD83C\uDF1F"),
    UPDATE("Обновить лот \uD83E\uDEF6"),
    DELETE("Удалить лот \uD83D\uDDD1"),
    TABLE("Таблица торгов \uD83E\uDDEE"),
    DOWNLOAD_TABLE("Скачать таблицу торгов"),
    CLEAR_BIDDING("Очистить таблицу торгов"),
    YES_CLEAR("Да"),
    NO("Нет");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
