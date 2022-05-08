package org.programmers.baedal.dto;

import org.programmers.baedal.model.Category;

public class CreateStoreRequest {
    private final String storeName;
    private final Category category;
    private final String address;
    private final long deliveryFee;

    public CreateStoreRequest(String storeName, Category category, String address, long deliveryFee) {
        this.storeName = storeName;
        this.category = category;
        this.address = address;
        this.deliveryFee = deliveryFee;
    }

    public String getName() {
        return storeName;
    }

    public Category getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public long getDeliveryFee() {
        return deliveryFee;
    }
}
