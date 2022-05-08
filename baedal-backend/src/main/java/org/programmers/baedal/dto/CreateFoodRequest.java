package org.programmers.baedal.dto;

public class CreateFoodRequest {
    private final String foodName;
    private final long price;
    private final String description;

    public CreateFoodRequest(String foodName, long price, String description) {
        this.foodName = foodName;
        this.price = price;
        this.description = description;
    }

    public String getFoodName() {
        return foodName;
    }

    public long getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
