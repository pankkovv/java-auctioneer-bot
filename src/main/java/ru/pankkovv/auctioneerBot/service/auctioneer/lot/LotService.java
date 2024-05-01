package ru.pankkovv.auctioneerBot.service.auctioneer.lot;

import ru.pankkovv.auctioneerBot.model.Lot;

public interface LotService {
    Lot create(Lot lot);
    Lot getLot(long id);
}
