package org.programmers.baedal.model;

import java.util.UUID;

public class OrderFood {
    private final UUID orderId;
    private final long foodId;
    private long price;
    private long quantity;

    public OrderFood(UUID orderId, long foodId, long price, long quantity) {
        this.orderId = orderId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public long getFoodId() {
        return foodId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
