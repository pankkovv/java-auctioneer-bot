package ru.pankkovv.auctioneerBot.telegram.exception;

/**
 * Исключение, пробрасываемое в случае получения невалидных настроек выгружаемого файла
 */
public class IllegalSettingsException extends IllegalArgumentException {

    public IllegalSettingsException(String s) {
        super(s);
    }
}