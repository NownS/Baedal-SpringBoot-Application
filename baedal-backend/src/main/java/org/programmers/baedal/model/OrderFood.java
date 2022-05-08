package org.programmers.baedal.model;

import java.util.UUID;

public class OrderFood {

    private final UUID storeId;
    private final UUID foodId;
    private long price;
    private long quantity;

    public OrderFood(UUID storeId, UUID foodId, long price, long quantity) {
        this.storeId = storeId;
        this.foodId = foodId;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public UUID getFoodId() {
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
