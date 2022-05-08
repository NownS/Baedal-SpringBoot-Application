package org.programmers.baedal.service;

import org.programmers.baedal.model.Order;
import org.programmers.baedal.model.OrderFood;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order createOrder(UUID storeId, UUID foodId);
    List<OrderFood> getOrderFoodsByOrderId(UUID orderId);
}
