package org.programmers.baedal.repository;

import org.programmers.baedal.model.Order;
import org.programmers.baedal.model.OrderFood;

import java.util.List;
import java.util.UUID;

public interface OrderRepository {
    Order insert(Order order);
    List<OrderFood> getOrderFoodsByOrderId(UUID orderId);
}
