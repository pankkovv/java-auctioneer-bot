package ru.pankkovv.auctioneerBot.service.auctioneer.lot;

import org.springframework.stereotype.Service;
import ru.pankkovv.auctioneerBot.exception.LotNotFoundException;
import ru.pankkovv.auctioneerBot.model.Lot;
import ru.pankkovv.auctioneerBot.repository.lot.LotRepository;

@Service
public class LotServiceImpl implements LotService {
    private LotRepository repository;

    @Override
    public Lot create(Lot lot) {
        return repository.save(lot);
    }

    @Override
    public Lot getLot(long id) {
        return repository.findById(id).orElseThrow(() -> new LotNotFoundException(""));
    }
}
