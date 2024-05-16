package ru.pankkovv.auctioneerBot.enums;

public enum ExceptionMessage {
    NOT_FOUND_COMMAND_EXCEPTION("Простите, я не понимаю Вас. Похоже, что Вы ввели сообщение, не соответствующее формату."),
    VALIDATE_BET_EXCEPTION("Напомню, если вы хотите предложить свою цену за лот, необходимо выполнить следующие условия:\n" +
            "1. Ваша цена не может быть меньше актуальной стоимости лота \n" +
            "2. Увеличение цены лота не может быть меньше установленного шага торгов \n" +
            "3. Увеличение цены лота должно быть кратно установленному шагу торгов\n\n" +
            "Попробуйте еще раз \uD83D\uDE0A"),
    NOT_FOUND_RULES_ADMIN_EXCEPTION("Для выполнения данной команды необходимы права администратора."),
    NOT_FOUND_LOT_EXCEPTION("Ни один лот еще не зарегистрирован."),
    NOT_FOUND_BET_EXCEPTION("Вы еще не делали ставку в аукционе, либо вы ставку удалили.");

    public final String label;

    ExceptionMessage(String label){
        this.label = label;
    }
}
