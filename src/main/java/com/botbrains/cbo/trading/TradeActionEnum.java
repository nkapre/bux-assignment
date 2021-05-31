package com.botbrains.cbo.trading;

public enum TradeActionEnum {
    BUY ("BUY"), SELL("SELL");

    String tradeAction;

    TradeActionEnum (String action) {
        this.tradeAction = action;
    }

    @Override
    public String toString() {
        return "TradeActionEnum{" +
                "tradeAction='" + tradeAction + '\'' +
                '}';
    }
}
