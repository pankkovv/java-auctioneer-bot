package ru.pankkovv.auctioneerBot.model;

import lombok.Builder;
import lombok.Data;

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

    public String toString() {
        return "name= " + this.getName() +
                ", start_price= " + this.getStartPrice() +
                ", current_price= " + this.getCurrentPrice() +
                ", step= " + this.getStep();
    }
}
