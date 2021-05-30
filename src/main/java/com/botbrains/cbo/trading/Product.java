package com.botbrains.cbo.trading;

import java.util.Objects;

public class Product {
    private String securityId;
    private String symbol;
    private String displayName;

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return securityId.equals(product.securityId) && symbol.equals(product.symbol) && displayName.equals(product.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(securityId, symbol, displayName);
    }

    @Override
    public String toString() {
        return "Product{" +
                "securityId='" + securityId + '\'' +
                ", symbol='" + symbol + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
