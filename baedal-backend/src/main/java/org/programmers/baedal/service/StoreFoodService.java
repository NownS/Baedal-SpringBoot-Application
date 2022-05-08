package org.programmers.baedal.service;

import org.programmers.baedal.model.Food;

import java.util.List;
import java.util.UUID;

public interface StoreFoodService {
    List<Food> getAllFoodsByStoreId(UUID storeId);
    Food createFood(UUID storeId, String foodName, long price, String description);

    void deleteFoodById(UUID foodId);
}
