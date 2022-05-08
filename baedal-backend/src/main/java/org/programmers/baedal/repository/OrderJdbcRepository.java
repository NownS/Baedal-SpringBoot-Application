package org.programmers.baedal.repository;

import org.programmers.baedal.model.Order;
import org.programmers.baedal.model.OrderFood;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.programmers.baedal.repository.JdbcUtils.toUUID;

@Repository
public class OrderJdbcRepository implements OrderRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Order insert(Order order) {
        jdbcTemplate.update("insert into orders(order_id, name, email, address, order_status, created_at, updated_at) " +
                "values (UUID_TO_BIN(:orderId), :name, :email, :address, :orderStatus, :createdAt, :updatedAt)",
                toOrderParamMap(order));
        order.getOrderFoods().forEach(item -> jdbcTemplate.update("insert into order_foods(order_id, store_id, food_id, price, quantity) " +
                "values (UUID_TO_BIN(:orderId), UUID_TO_BIN(:storeId), UUID_TO_BIN(:foodId), :price, :quantity)",
                toOrderFoodParamMap(order.getOrderId(), item)));
        return order;
    }

    @Override
    public List<OrderFood> getOrderFoodsByOrderId(UUID orderId) {
        return jdbcTemplate.query("select * from order_foods where order_id = UUID_TO_BIN(:orderId)",
                Collections.singletonMap("orderId", orderId.toString().getBytes()),
                orderFoodRowMapper
        );
    }

    private static final RowMapper<OrderFood> orderFoodRowMapper = (resultSet, i) -> {
        UUID storeId = toUUID(resultSet.getBytes("store_id"));
        UUID foodId = toUUID(resultSet.getBytes("food_id"));
        long price = resultSet.getLong("price");
        int quantity = resultSet.getInt("quantity");

        return new OrderFood(
                storeId,
                foodId,
                price,
                quantity
        );
    };

    private Map<String, Object> toOrderParamMap(Order order) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", order.getOrderId().toString().getBytes());
        paramMap.put("name", order.getName());
        paramMap.put("email", order.getEmail().getAddress());
        paramMap.put("address", order.getAddress());
        paramMap.put("orderStatus", order.getOrderStatus().toString());
        paramMap.put("createdAt", order.getCreatedAt());
        paramMap.put("updatedAt", order.getUpdatedAt());
        return paramMap;
    }

    private Map<String, Object> toOrderFoodParamMap(UUID orderId, OrderFood orderFood) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderId", orderId.toString().getBytes());
        paramMap.put("storeId", orderFood.getStoreId().toString().getBytes());
        paramMap.put("foodId", orderFood.getFoodId().toString().getBytes());
        paramMap.put("price", orderFood.getPrice());
        paramMap.put("quantity", orderFood.getQuantity());
        return paramMap;
    }
}
