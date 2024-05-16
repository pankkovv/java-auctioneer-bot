package ru.pankkovv.auctioneerBot.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

/**
 * Торгуемый лот
 */
@Data
@Builder
public class Lot {
    private String name;
    private Float startPrice;
    private Float currentPrice;
    private Float step;
    private File photo;

    public String toString() {
        return this.getName().toUpperCase() +
                "\nНачальная цена: " + this.getStartPrice() +
                "\nТекущая цена: " + this.getCurrentPrice() +
                "\nШаг ставки: " + this.getStep();
    }
}
