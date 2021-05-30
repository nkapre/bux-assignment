package com.botbrains.cbo.trading;

public enum DirectionEnum {
    BUY ("BUY"), SELL ("SELL"), IGNORE("IGNORE");
    String direction;
    DirectionEnum(String directionString) {
        this.direction = directionString;
    }


}
