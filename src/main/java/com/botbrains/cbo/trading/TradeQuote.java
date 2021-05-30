package com.botbrains.cbo.trading;

import com.google.gson.annotations.SerializedName;

public class TradeQuote {
    @SerializedName("t")
    private String messageType = "trading.quote";
    private Body body;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public boolean isForProduct (String productId) {
        return body.getSecurityId().equalsIgnoreCase(productId);
    }

    public class Body {
        private String securityId;
        private String currentPrice;

        public String getSecurityId() {
            return securityId;
        }

        public void setSecurityId(String securityId) {
            this.securityId = securityId;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }

        @Override
        public String toString() {
            return "Body{" +
                    "securityId='" + securityId + '\'' +
                    ", currentPrice='" + currentPrice + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TradeQuote{" +
                "messageType='" + messageType + '\'' +
                ", body=" + body +
                '}';
    }
}
