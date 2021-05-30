package com.botbrains.utils;

import com.botbrains.cbo.messaging.MessageType;
import io.micronaut.core.util.StringUtils;

/**
 * Utilities for mesage handling
 */
public class MessageUtils {
    public static final MessageType getMessageType (String message) {
        if (StringUtils.isEmpty(message)) {
            return MessageType.UNKNOWN;
        }

        if (message.toLowerCase().contains("connect.")) {
            return MessageType.CONNECT_MESSAGE;
        }
        else if (message.toLowerCase().contains("subscribeto")) {
            return MessageType.SUBSCRIBE_MESSAGE;
        }
        else if (message.toLowerCase().contains("trading.quote")) {
            return MessageType.TRADE_STATUS_MESSAGE;
        }

        return MessageType.UNKNOWN;
    }
}
