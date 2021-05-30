package com.botbrains.client;

import com.botbrains.cbo.trading.TradeOrder;
import com.google.gson.Gson;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * The client for performing REST operations related to buy and sell.
 */
@Singleton
public class BotBrainsRestCient {
    private static final Logger log = LoggerFactory.getLogger(BotBrainsRestCient.class);

    @Inject
    private RxHttpClient httpClient;

    @Value("${trade.server.buyUrl}")
    private String buyUrl;

    @Value("${trade.server.sellUrl}")
    private String sellUrl;

    @Value("${micronaut.authKey}")
    private String authKey;

    @Value("${micronaut.lang}")
    private String lang;

    public void buyOrder(TradeOrder tradeOrder) {
        Gson gson = new Gson();
        String buyOrderJsonString = gson.toJson(tradeOrder, tradeOrder.getClass());

        HttpRequest buyRequest = HttpRequest.POST(buyUrl, buyOrderJsonString)
                .header("Authorization", authKey)
                .header("Accept-Language", lang)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        Flowable<String> flowable = httpClient.retrieve(buyRequest);
        Maybe<String> response = flowable.firstElement();

        log.info("Response for buy trade is {}", response.blockingGet());

    }

    public void sellOrder (TradeOrder tradeOrder) {
        HttpRequest deleteRequest = HttpRequest.DELETE(buyUrl + tradeOrder.getProductId())
                .header("Authorization", authKey)
                .header("Accept-Language", lang)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json");

        Flowable<String> flowable = httpClient.retrieve(deleteRequest);
        Maybe<String> response = flowable.firstElement();

        log.info("Response for sell trade is {}", response.blockingGet());
    }


}
