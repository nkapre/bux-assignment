package com.botbrains.cbo.trading;

public class TradeOrder {
    private String productId;
    private MoneyAmountDetails investingAmount;
    private int leverage;
    private DirectionEnum direction;
    private Source source;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public MoneyAmountDetails getInvestingAmount() {
        return investingAmount;
    }

    public void setInvestingAmount(MoneyAmountDetails investingAmount) {
        this.investingAmount = investingAmount;
    }

    public int getLeverage() {
        return leverage;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public static class Source {
        String sourceType;

        public Source(String sourceType) {
            this.sourceType = sourceType;
        }

        public String getSourceType() {
            return sourceType;
        }

        public void setSourceType(String sourceType) {
            this.sourceType = sourceType;
        }
    }
}
