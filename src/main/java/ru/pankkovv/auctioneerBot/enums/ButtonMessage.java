package ru.pankkovv.auctioneerBot.enums;

public enum ButtonMessage {
    START("Главное меню"),
    HELP("Помощь"),
    LOT("Посмотреть лот"),
    CREATE_BET("Сделать ставку"),
    GET_BET("Посмотреть свою ставку"),
    CANCEL("Отменить ставку"),
    REGISTRATION("Регистрация"),
    CREATE("Создать лот"),
    UPDATE("Обновить лот"),
    DELETE("Удалить лот"),
    TABLE("Таблица торгов");

    public final String label;

    ButtonMessage(String label) {
        this.label = label;
    }
}
