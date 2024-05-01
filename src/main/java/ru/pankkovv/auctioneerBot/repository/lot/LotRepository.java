package ru.pankkovv.auctioneerBot.repository.lot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.auctioneerBot.model.Lot;

@Repository
public interface LotRepository extends JpaRepository <Lot, Long> {
}
