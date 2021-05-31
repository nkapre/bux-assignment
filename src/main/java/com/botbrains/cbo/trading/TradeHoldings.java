package com.botbrains.cbo.trading;

import java.util.HashMap;
import java.util.Map;

public class TradeHoldings {
    private Map<String, Product> productsBought;

    public TradeHoldings() {
        this.productsBought = new HashMap<>();
    }

    public Map<String, Product> getProductsBought() {
        return productsBought;
    }

    public void setProductsBought(Map<String, Product> productsBought) {
        this.productsBought = productsBought;
    }

    public void addToHoldings(Product product) {
        this.productsBought.put(product.getSecurityId(), product);
    }

    public boolean hasHolding (Product product) {
        return this.productsBought.containsKey(product.getSecurityId());
    }

    public boolean hasHolding (String productId) {
        return this.productsBought.containsKey(productId);
    }

    public void removeFromHolding (Product product) {
        this.productsBought.remove(product.getSecurityId());
    }
}
