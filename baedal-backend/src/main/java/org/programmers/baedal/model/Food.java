package org.programmers.baedal.model;

import java.time.LocalDateTime;

public class Food {
    private final long id;
    private final long storeId;
    private final String name;
    private final long price;
    private final String description;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Food(long id, long storeId, String name, long price, String description) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getStoreId() {
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
}
