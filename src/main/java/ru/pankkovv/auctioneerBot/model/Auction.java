package ru.pankkovv.auctioneerBot.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Аукцион
 */
public class Auction {
    public static Lot lot;
    public static List<String> admin = new ArrayList<>();
    public static Map<String, Float> bidding = new HashMap<>();
    public static boolean flag = true;
}
