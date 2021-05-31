package com.botbrains.cbo.trading;

public enum TypeEnum {
    OPEN ("OPEN"), CLOSE ("CLOSE");
    String type;
    TypeEnum (String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.type;
    }
}
