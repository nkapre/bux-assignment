package com.botbrains.cbo.trading;

/**
 * Class that encapsulates the trade parameters specified during startup.
 * There was ambiguity about some other parameters such as
 * investmentAmount
 * currency
 * decimals
 * source
 * leverage
 * For those assumptions have been made.
 */
public class TradeParameters {
    private String productId;
    private String buyPrice;
    private String upperLimitSellPrice;
    private String lowerLimitSellPrice;
    private String investmentAmount;
    private String curency;
    private int decimals;
    private String source;
    private int leverage;

    public TradeParameters(String productId, String buyPrice, String upperLimitSellPrice,
                           String lowerLimitSellPrice) {
        this.productId = productId;
        this.buyPrice = buyPrice;
        this.upperLimitSellPrice = upperLimitSellPrice;
        this.lowerLimitSellPrice = lowerLimitSellPrice;
        this.investmentAmount = "500";
        this.decimals = 2;
        this.source = "OTHER";
        this.curency = "BUX";
        this.leverage = 2;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getUpperLimitSellPrice() {
        return upperLimitSellPrice;
    }

    public void setUpperLimitSellPrice(String upperLimitSellPrice) {
        this.upperLimitSellPrice = upperLimitSellPrice;
    }

    public String getLowerLimitSellPrice() {
        return lowerLimitSellPrice;
    }

    public void setLowerLimitSellPrice(String lowerLimitSellPrice) {
        this.lowerLimitSellPrice = lowerLimitSellPrice;
    }

    public String getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(String investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public String getCurency() {
        return curency;
    }

    public void setCurency(String curency) {
        this.curency = curency;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getLeverage() {
        return leverage;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    @Override
    public String toString() {
        return "TradeParameters{" +
                "productId='" + productId + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", upperLimitSellPrice='" + upperLimitSellPrice + '\'' +
                ", lowerLimitSellPrice='" + lowerLimitSellPrice + '\'' +
                ", investmentAmount='" + investmentAmount + '\'' +
                ", curency='" + curency + '\'' +
                ", decimals=" + decimals +
                ", source='" + source + '\'' +
                ", leverage=" + leverage +
                '}';
    }
}
