package ru.pankkovv.auctioneerBot.enums;

public enum ButtonData {
    START_BTN("start_btn"),
    HELP_BTN("help_btn"),
    LOT_BTN("lot_btn"),
    CREATE_BET_BTN("create_bet_btn"),
    GET_BET_BTN("get_bet_btn"),
    CANCEL_BTN("cancel_btn"),
    REGISTRATION_BTN("registration_btn"),
    CREATE_BTN("create_btn"),
    UPDATE_BTN("update_btn"),
    DELETE_BTN("delete_btn"),
    TABLE_BTN("table_btn"),
    DOWNLOAD_TABLE_BTN("download_btn"),
    CLEAR_BIDDING_BTN("clear_bidding_btn"),
    YES_CLEAR_BTN("yes_clear_btn"),
    NO_BTN("no_btn");

    public final String label;

    ButtonData(String label) {
        this.label = label;
    }
}
