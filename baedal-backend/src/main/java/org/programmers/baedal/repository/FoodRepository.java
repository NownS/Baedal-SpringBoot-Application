package org.programmers.baedal.repository;

import org.programmers.baedal.model.Food;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodRepository {

    List<Food> findByStoreId(UUID storeId);
    Food insert(Food food);
    Food update(Food food);
    Optional<Food> findById(UUID foodId);
    Optional<Food> findByNameAndStoreId(String foodName, UUID storeId);
    void deleteByFoodId(UUID foodId);
}
