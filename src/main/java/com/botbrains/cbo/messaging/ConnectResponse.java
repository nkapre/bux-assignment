package com.botbrains.cbo.messaging;

public class ConnectResponse {
    private String userId;
    private String sessionId;
    private String time;
    private String t;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public boolean isConnected() {
        return this.getT().equals("connect.connected");
    }
}
