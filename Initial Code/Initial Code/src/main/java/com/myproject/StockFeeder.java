package com.myproject;

import java.util.*;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        if (!stockList.contains(stock)) {
            stockList.add(stock);
        }
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        boolean flag = false;
        for (Stock stock : stockList) {
            if (stock.getCode() == code) { 
                viewers.computeIfAbsent(code, k -> new ArrayList<>()).add(stockViewer); 
                flag = true;
            }
        }
        if (!flag) Logger.errorRegister(code);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        List<StockViewer> ListViewer = viewers.get(code);
        if (ListViewer == null) Logger.errorUnregister(code);
        else {
            ListViewer.remove(stockViewer);
            viewers.remove(code);
            viewers.put(code, ListViewer); 
        }
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        String code = stockPrice.getCode();
        List<StockViewer> ListViewer = viewers.get(code);
        for (StockViewer viewer : ListViewer) {
            viewer.onUpdate(stockPrice);
        }         
    }
}
