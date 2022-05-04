package org.programmers.baedal.model;

import java.time.LocalDateTime;

public class Store {
    private final long id;
    private final String name;
    private boolean isOpened;
    private final Category category;
    private final String address;
    private long deliveryFee;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Store(long id, String name, boolean isOpened, Category category, String address, long deliveryFee) {
        this.id = id;
        this.name = name;
        this.isOpened = isOpened;
        this.category = category;
        this.address = address;
        this.deliveryFee = deliveryFee;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Store(long id, String name, Category category, String address) {
        this(id, name, false, category, address, 3000);
    }

    public Store(long id, String name, Category category, String address, long deliveryFee) {
        this(id, name, false, category, address, deliveryFee);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isOpened() {
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
