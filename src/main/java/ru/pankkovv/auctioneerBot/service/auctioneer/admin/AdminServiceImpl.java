package ru.pankkovv.auctioneerBot.service.auctioneer.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pankkovv.auctioneerBot.exception.AdminNotFoundException;
import ru.pankkovv.auctioneerBot.model.Admin;
import ru.pankkovv.auctioneerBot.repository.admin.AdminRepository;

/**
 * Команда "Старт"
 */
@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    private AdminRepository repository;
    @Override
    public Admin create(String username) {
        return repository.save(Admin.builder().username(username).build());
    }

    @Override
    public Admin getUser(String username) {
        return repository.getUserBotByUsername(username).orElseThrow(() -> new AdminNotFoundException(""));
    }
}