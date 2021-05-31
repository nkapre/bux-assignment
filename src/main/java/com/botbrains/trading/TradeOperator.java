package com.botbrains.trading;

import com.botbrains.cbo.trading.*;
import com.botbrains.cbo.subscription.SubscriptionRequest;
import com.botbrains.client.BotBrainsRestCient;
import com.botbrains.client.BotBrainsWebSocketClient;
import com.google.gson.Gson;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.websocket.RxWebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The class that interfaces with the actual WebSocket client and decides on what needs to be done with the
 * message. In that sense, this is a central co-ordinator that handles delegation tasks.
 */
@Singleton
public class TradeOperator {
    private static final Logger log = LoggerFactory.getLogger(TradeOperator.class);

    @Inject
    private BotBrainsWebSocketClient bbwsc;

    @Inject
    private BotBrainsRestCient restClient;

    @Inject
    private Trader trader;

    @Value("${trade.server.url}")
    private String tradeServerUrl;

    @Value("${micronaut.authKey}")
    private String authKey;

    @Value("${micronaut.lang}")
    private String lang;

    private TradeParameters tradeParameters;
    public TradeOperator(RxWebSocketClient client) {
    }

    /**
     * Start the trade activity. This will involve initializing the interface with the end trading server.
     * @param params
     */
    public void start(TradeParameters params) {
        log.info("BotBrains starting the trade process");
        this.tradeParameters = params;
        HttpRequest req = HttpRequest.GET(tradeServerUrl)
                .header("Authorization", authKey)
                .header("Accepted-Language", lang);

        MutableHttpRequest muReq = (MutableHttpRequest) req;
        bbwsc.initializeConnections();
        bbwsc.setTradeOperator(this);
        trader.setTradeParameters(this.tradeParameters);
    }

    /**
     * This is invoked when the WebSocket client has decided that the conneciton is successful and
     * we can safely proceed ahead with the futher activities.
     *
     * This will lead to the issue of the subscribe message to the trade server via WebSocket client.
     */
    public void readyForSubscribe() {
        String[] subscribedTo = {this.tradeParameters.getProductId()};
        String[] unsbscribedFrom = {};
        SubscriptionRequest request = new SubscriptionRequest(subscribedTo, unsbscribedFrom);
        Gson gson = new Gson();

        bbwsc.sendSubscribeMessage(gson.toJson(request, SubscriptionRequest.class));
    }

    /**
     * Subscribe is successful, now we can set the Product ID for the trader to work on.
     */
    public void successfulSubscribe() {
        trader.setProductToTrade(this.tradeParameters.getProductId());
    }

    /**
     * A trade status message has come in. This message will decide what needs to be done.
     * Ask the Trader what we need to do. Shall we buy or shall we sell. Accordingly, the trader
     * will issue a TradeOrder.
     *
     * @param message
     */
    public void onStatusMessage (String message) {
        Gson gson = new Gson();
        TradeQuote tradeQuote = gson.fromJson(message, TradeQuote.class);
        log.info("Trader to work on [{}]", tradeQuote.toString());

        //Only if this is for a product we are interested in, should we act on it.
        if (tradeQuote.isForProduct(tradeParameters.getProductId())) {
            TradeOrder tradeOrder = trader.handleTradeQuote(tradeQuote);
            TradeOrderResponse response = null;

            if (tradeOrder.getDirection() == DirectionEnum.BUY) {
                response = restClient.buyOrder(tradeOrder);
                trader.updateHolding(response.getProduct(), TradeActionEnum.BUY);
            }
            else if (tradeOrder.getDirection() == DirectionEnum.SELL) {
                response = restClient.sellOrder(tradeOrder);
                trader.updateHolding(response.getProduct(), TradeActionEnum.SELL);
            }
        }
    }

    /**
     * Exit the whole thing. Anything that needed to be cleaned has been cleaned out.
     */
    public void onClose() {
        System.exit(1);
    }
}
