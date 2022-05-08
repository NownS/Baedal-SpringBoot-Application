package org.programmers.baedal.service;

import org.programmers.baedal.model.*;
import org.programmers.baedal.repository.FoodRepository;
import org.programmers.baedal.repository.OrderRepository;
import org.programmers.baedal.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;
    private final StoreRepository storeRepository;

    public DefaultOrderService(OrderRepository orderRepository, FoodRepository foodRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Order createOrder(UUID storeId, UUID foodId) {
        Optional<Food> foodOptional = foodRepository.findById(foodId);
        foodOptional.orElseThrow(() -> new RuntimeException("No Food."));
        Food food = foodOptional.get();
        Optional<Store> storeOptional = storeRepository.findById(storeId);
        storeOptional.orElseThrow(() -> new RuntimeException("No Store."));
        Store store = storeOptional.get();
        OrderFood orderFood = new OrderFood(storeId, foodId, store.getDeliveryFee() + food.getPrice(), 1);
        Order order = new Order(
                UUID.randomUUID(),
                "Anonymous",
                new Email("admin@admin.com"),
                "Anonymous-Address",
                OrderStatus.ORDERED,
                Collections.singletonList(orderFood)
        );
        return orderRepository.insert(order);
    }

    @Override
    public List<OrderFood> getOrderFoodsByOrderId(UUID orderId) {
        return orderRepository.getOrderFoodsByOrderId(orderId);
    }
}
