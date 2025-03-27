package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        for (String code : lastPrices.keySet()) {
            if (code == stockPrice.getCode() && lastPrices.get(code) != stockPrice.getAvgPrice()) {
                Logger.logRealtime(stockPrice.getCode(), stockPrice.getAvgPrice());
            }
        }
        lastPrices.put(stockPrice.getCode(), stockPrice.getAvgPrice());
    }
}
