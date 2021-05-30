package com.botbrains.cbo.trading;

import java.util.HashMap;
import java.util.Map;

public class TradeHoldings {
    private Map<String, String> productIdsBought;

    public TradeHoldings() {
        this.productIdsBought = new HashMap<>();
    }

    public Map<String, String> getProductIdsBought() {
        return productIdsBought;
    }

    public void setProductIdsBought(Map<String, String> productIdsBought) {
        this.productIdsBought = productIdsBought;
    }

    public void addToHoldings(String productId) {
        this.productIdsBought.put(productId, productId);
    }

    public boolean hasHolding (String productId) {
        return this.productIdsBought.containsKey(productId);
    }


}
