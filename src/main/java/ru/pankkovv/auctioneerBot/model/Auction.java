package ru.pankkovv.auctioneerBot.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Auction {
    public static Lot lot;
    public static List<String> admin = new ArrayList<>();
    public static Map<String, Float> bidding = new HashMap<>();
}
