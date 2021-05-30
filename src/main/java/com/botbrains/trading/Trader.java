package com.botbrains.trading;

import com.botbrains.cbo.trading.TradeParameters;
import com.botbrains.cbo.trading.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

/**
 * The class that specializes in the Trading operations that include
 * 1. Understanding the trade message from the server and deciding on the buy/sell/ignore
 * 2. Creating the buy order
 * 3. Creating the sell order
 */
@Singleton
public class Trader {
    private static final Logger log = LoggerFactory.getLogger(Trader.class);
    private String tradeProductId;
    private TradeParameters tradeParameters;
    private TradeHoldings holdings;

    public Trader () {
        this.holdings = new TradeHoldings();
    }

    public TradeOrder handleTradeQuote(TradeQuote tradeQuote) {
        if (!tradeQuote.isForProduct(tradeParameters.getProductId())) {
            return null;
        }

        if (this.holdings.getProductIdsBought().isEmpty()) {
            // Buy if applicable
            if (!this.holdings.hasHolding(tradeQuote.getBody().getSecurityId()) &&
            buyingConditionMet(tradeQuote)) {
                return buyRequest();
            }
        }
        else {
            if (this.holdings.hasHolding(tradeQuote.getBody().getSecurityId()) &&
                    sellingConditionsMet(tradeQuote)) {
                return sellRequest();
            }
        }

        TradeOrder order = new TradeOrder();
        order.setDirection(DirectionEnum.IGNORE);

        return order;
    }

    public TradeOrder buyRequest() {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setProductId(tradeParameters.getProductId());
        tradeOrder.setDirection(DirectionEnum.BUY);
        MoneyAmountDetails money = new MoneyAmountDetails();
        money.setAmount(tradeParameters.getInvestmentAmount());
        money.setCurrency(tradeParameters.getCurency());
        money.setDecimals(tradeParameters.getDecimals());

        tradeOrder.setInvestingAmount(money);
        tradeOrder.setLeverage(tradeParameters.getLeverage());
        tradeOrder.setSource(new TradeOrder.Source(tradeParameters.getSource()));

        return tradeOrder;
    }

    public TradeOrder sellRequest() {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setProductId(tradeParameters.getProductId());
        tradeOrder.setDirection(DirectionEnum.SELL);

        return tradeOrder;
    }

    //Only buy when the current product price in market is lesser than the user specified buy price
    private boolean buyingConditionMet(TradeQuote tradeQuote) {
        log.info("Current price is {} and buying price is {}",
                tradeQuote.getBody().getCurrentPrice(), tradeParameters.getBuyPrice());

        Double currentPrice = Double.parseDouble(tradeQuote.getBody().getCurrentPrice());
        Double buyingPrice = Double.parseDouble(tradeParameters.getBuyPrice());

        return currentPrice <= buyingPrice;
    }

    //Only sell when the current product price is in the range of the lower and upper sell prices
    private boolean sellingConditionsMet (TradeQuote tradeQuote) {
        Double currentPrice = Double.parseDouble(tradeQuote.getBody().getCurrentPrice());
        Double upperSellPrice = Double.parseDouble(tradeParameters.getUpperLimitSellPrice());
        Double lowerSellPrice = Double.parseDouble(tradeParameters.getLowerLimitSellPrice());

        return currentPrice >= lowerSellPrice && currentPrice <= upperSellPrice;
    }

    public void setProductToTrade(String productId) {
        this.tradeProductId = productId;
    }

    public void setTradeParameters(TradeParameters tradeParameters) {
        this.tradeParameters = tradeParameters;
    }
}
