package ru.pankkovv.auctioneerBot.enums;

public enum Command {
    START("start"),
    HELP("help"),
    LOT("lot"),
    CREATE_BET("create_bet"),
    GET_BET("get_bet"),
    CANCEL("cancel"),
    REGISTRATION("registration"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete"),
    TABLE("table");

    public final String label;

    Command(String label) {
        this.label = label;
    }
}
