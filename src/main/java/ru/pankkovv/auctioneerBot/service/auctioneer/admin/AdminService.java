package ru.pankkovv.auctioneerBot.service.auctioneer.admin;

import ru.pankkovv.auctioneerBot.model.Admin;

public interface AdminService {
    Admin create(String username);
    Admin getUser(String username);
}
