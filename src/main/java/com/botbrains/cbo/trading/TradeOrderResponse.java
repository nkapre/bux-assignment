package com.botbrains.cbo.trading;

public class TradeOrderResponse {
    private String id;
    private String positionId;
    private Product product;
    private MoneyAmountDetails investingAmount;
    private MoneyAmountDetails price;
    private int leverage;
    private DirectionEnum direction;
    private TypeEnum type;
    private String dateCreated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MoneyAmountDetails getInvestingAmount() {
        return investingAmount;
    }

    public void setInvestingAmount(MoneyAmountDetails investingAmount) {
        this.investingAmount = investingAmount;
    }

    public MoneyAmountDetails getPrice() {
        return price;
    }

    public void setPrice(MoneyAmountDetails price) {
        this.price = price;
    }

    public int getLeverage() {
        return leverage;
    }

    public void setLeverage(int leverage) {
        this.leverage = leverage;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "TradeOrderResponse{" +
                "id='" + id + '\'' +
                ", positionId='" + positionId + '\'' +
                ", product=" + product +
                ", investingAmount=" + investingAmount +
                ", price=" + price +
                ", leverage=" + leverage +
                ", direction=" + direction +
                ", type=" + type +
                ", dateCreated='" + dateCreated + '\'' +
                '}';
    }
}
