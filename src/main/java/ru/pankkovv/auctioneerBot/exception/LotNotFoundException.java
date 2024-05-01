package ru.pankkovv.auctioneerBot.exception;

public class LotNotFoundException extends RuntimeException {
    public LotNotFoundException(String message) {
        super(message);
    }
}
