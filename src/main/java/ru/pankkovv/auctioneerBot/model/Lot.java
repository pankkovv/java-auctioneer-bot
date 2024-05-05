package ru.pankkovv.auctioneerBot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Lot {
    private String name;
    private Float startPrice;
    private Float step;

    public String toString() {
        return "name=" + this.getName() + ", price=" + this.getStartPrice() + ", step=" + this.getStep();
    }
}
