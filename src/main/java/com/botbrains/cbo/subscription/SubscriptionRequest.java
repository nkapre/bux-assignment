package com.botbrains.cbo.subscription;

public class SubscriptionRequest {
    private String[] subscribeTo;
    private String[] unsubscribeFrom;

    public SubscriptionRequest(String[] subscribeTo, String[] unsubscribeFrom) {
        this.subscribeTo = new String[subscribeTo.length];
        this.unsubscribeFrom = new String[unsubscribeFrom.length];

        for (int i = 0; i < subscribeTo.length; i++) {
            this.subscribeTo[i] = "trading.product." + subscribeTo[i];
        }

        for (int i = 0; i < unsubscribeFrom.length; i++) {
            this.unsubscribeFrom[i] = "trading.product." + unsubscribeFrom[i];
        }

    }

    public String[] getSubscribeTo() {
        return subscribeTo;
    }

    public void setSubscribeTo(String[] subscribeTo) {
        this.subscribeTo = subscribeTo;
    }

    public String[] getUnsubscribeFrom() {
        return unsubscribeFrom;
    }

    public void setUnsubscribeFrom(String[] unsubscribeFrom) {
        this.unsubscribeFrom = unsubscribeFrom;
    }
}
