package com.botbrains.client;

import com.botbrains.cbo.messaging.ConnectResponse;
import com.botbrains.cbo.messaging.MessageType;
import com.botbrains.exceptions.BotBrainsServerConnectionException;
import com.botbrains.exceptions.UnableToPerformOperationException;
import com.botbrains.trading.TradeOperator;
import com.botbrains.utils.MessageUtils;
import com.google.gson.Gson;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpHeaders;
import io.micronaut.websocket.annotation.OnError;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;

/**
 * The class that interfaces with the WEbSocket server. This class has a cyclic depndency with the
 * TradeOperator
 * Events from the WebSocket server are raied to the TradeOperator, which in turn delegates requests to teh
 * necessary classes.
 */
@WebSocket
public class BotBrainsWebSocketClient {
    private static final Logger log = LoggerFactory.getLogger(BotBrainsWebSocketClient.class);

    private WebSocketClient webSocketClient;
    private Session session;

    @Value("${trade.server.url}")
    private String tradeServerUrl;

    @Value("${micronaut.authKey}")
    private String authHeader;

    @Value("${micronaut.lang}")
    private String lang;

    private boolean alreadyConnected;
    private boolean subscribed;

    private TradeOperator tradeOperator;

    public BotBrainsWebSocketClient() {
        this.webSocketClient = new WebSocketClient();
    }

    /**
     * Initialize connection with the WebSocket server.
     */
    public void initializeConnections() {
        try {
            webSocketClient.start();
            URI quoteFeedUri = new URI(tradeServerUrl);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
            request.setHeader(HttpHeaders.ACCEPT_LANGUAGE, lang);
            webSocketClient.connect(this,quoteFeedUri,request);

            log.info("Connection with the BotBrains server initialized...");
        } catch (Exception e) {
            throw new BotBrainsServerConnectionException("Unable to connect to BotBrains server. Exception [{}] occurred", e);
        }

    }

    /**
     * Message has been received from the WebSocket server. This method decides what needs to be done with it.
     * @param msg
     */
    @OnWebSocketMessage
    public void onMessage(String msg) {
        log.debug("Got message in handler from server: {}", msg);

        if(MessageUtils.getMessageType(msg) == MessageType.CONNECT_MESSAGE){
            this.alreadyConnected = true;
            log.info("Connected message received successfully from server. Moving to subscriptions...");
            this.tradeOperator.readyForSubscribe();
        }
        else if (MessageUtils.getMessageType(msg) == MessageType.SUBSCRIBE_MESSAGE) {
            log.info("Subscription made successfully. Server says [{}]", msg);
        }
        else if (MessageUtils.getMessageType(msg) == MessageType.TRADE_STATUS_MESSAGE) {
            this.tradeOperator.onStatusMessage(msg);
        }
    }

    private boolean isConnected (String message) {
        Gson gson = new Gson();
        ConnectResponse resp = gson.fromJson(message, ConnectResponse.class);

        return resp.isConnected();
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        log.info("Connection closed: {} - {}", statusCode, reason);
        this.session = null;
        tradeOperator.onClose();
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        log.info("Got connect: {}", session);
        this.session = session;
    }

    public void setTradeOperator(TradeOperator tradeOperator) {
        this.tradeOperator = tradeOperator;
    }

    public void sendSubscribeMessage(String message) {
        try {
            session.getRemote().sendString(message);
            tradeOperator.successfulSubscribe();
        } catch (IOException e) {
            throw new UnableToPerformOperationException("Unable to perform Subscribe operation", e);
        }
    }

    @OnError
    public void onError(Throwable error) {
        log.info("onError [{}]", error);
    }
}
