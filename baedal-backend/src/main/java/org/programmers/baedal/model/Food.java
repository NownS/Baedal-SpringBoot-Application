package org.programmers.baedal.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Food {
    private final UUID foodId;
    private final UUID storeId;
    private final String name;
    private final long price;
    private final String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Food(UUID foodId, UUID storeId, String name, long price, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.foodId = foodId;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Food(UUID foodId, UUID storeId, String name, long price, String description) {
        this(foodId, storeId, name, price, description, LocalDateTime.now(), LocalDateTime.now());
    }

    public UUID getFoodId() {
        return foodId;
    }

    public UUID getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
