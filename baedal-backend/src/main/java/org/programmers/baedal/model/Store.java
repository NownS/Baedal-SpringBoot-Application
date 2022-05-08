package org.programmers.baedal.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Store {
    private final UUID storeId;
    private final String name;
    private Boolean isOpened;
    private final Category category;
    private final String address;
    private long deliveryFee;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Store(UUID storeId, String name, Boolean isOpened, Category category, String address, long deliveryFee, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.storeId = storeId;
        this.name = name;
        this.isOpened = isOpened;
        this.category = category;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Store(UUID storeId, String name, Boolean isOpened, Category category, String address, long deliveryFee) {
        this(storeId, name, isOpened, category, address, deliveryFee, LocalDateTime.now(), LocalDateTime.now());
    }

    public Store(UUID storeId, String name, Category category, String address) {
        this(storeId, name, true, category, address, 3000);
    }

    public Store(UUID storeId, String name, Category category, String address, long deliveryFee) {
        this(storeId, name, true, category, address, deliveryFee);
    }

    public UUID getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public Boolean isOpened() {
        return isOpened;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setOpened(boolean opened) {
        this.isOpened = opened;
        this.updatedAt = LocalDateTime.now();
    }

    public void setDeliveryFee(long deliveryFee) {
        this.deliveryFee = deliveryFee;
        this.updatedAt = LocalDateTime.now();
    }
}
