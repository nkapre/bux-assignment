package com.botbrains.cbo.trading;

import java.util.Objects;

public class MoneyAmountDetails {
    private String currency;
    private int decimals;
    private String amount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneyAmountDetails that = (MoneyAmountDetails) o;
        return decimals == that.decimals && currency.equals(that.currency) && amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, decimals, amount);
    }

    @Override
    public String toString() {
        return "InvestmentAmount{" +
                "currency='" + currency + '\'' +
                ", decimals=" + decimals +
                ", amount='" + amount + '\'' +
                '}';
    }
}
