package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement alert logic based on threshold conditions
        String code = stockPrice.getCode();
        if (lastAlertedPrices.isEmpty() || lastAlertedPrices.get(code) == null || (stockPrice.getAvgPrice() != lastAlertedPrices.get(code))) {
            if (stockPrice.getAvgPrice() >= alertThresholdHigh) {
                alertAbove(stockPrice.getCode(), stockPrice.getAvgPrice()); 
            } 
            else if (stockPrice.getAvgPrice() <= alertThresholdLow) {
                alertBelow(stockPrice.getCode(), stockPrice.getAvgPrice());
            }
            lastAlertedPrices.put(stockPrice.getCode(), stockPrice.getAvgPrice());
        }
    }

    private void alertAbove(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
        //Logger.notImplementedYet("alertAbove");
    }

    private void alertBelow(String stockCode, double price) {
        // TODO: Call Logger to log the alert
        Logger.logAlert(stockCode, price);
        //Logger.notImplementedYet("alertBelow");
    }
}
