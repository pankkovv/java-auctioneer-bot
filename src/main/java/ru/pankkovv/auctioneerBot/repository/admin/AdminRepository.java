package ru.pankkovv.auctioneerBot.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pankkovv.auctioneerBot.model.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> getUserBotByUsername(String username);
}
