package ru.pankkovv.auctioneerBot.repository.lot;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pankkovv.auctioneerBot.model.Lot;

public interface LotRepository extends JpaRepository <Lot, Long> {
}
