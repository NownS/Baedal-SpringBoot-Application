package org.programmers.baedal.service;

import org.programmers.baedal.model.Food;
import org.programmers.baedal.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultStoreFoodService implements StoreFoodService {

    private final FoodRepository foodRepository;

    public DefaultStoreFoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getAllFoodsByStoreId(UUID storeId) {
        return foodRepository.findByStoreId(storeId);
    }

    @Override
    public Food createFood(UUID storeId, String foodName, long price, String description) {
        Food food = new Food(UUID.randomUUID(), storeId, foodName, price, description);
        return foodRepository.insert(food);
    }

    @Override
    public void deleteFoodById(UUID foodId) {
        foodRepository.deleteByFoodId(foodId);
    }
}
