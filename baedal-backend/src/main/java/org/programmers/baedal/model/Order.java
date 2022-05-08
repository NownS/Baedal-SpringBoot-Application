package org.programmers.baedal.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final String name;
    private final Email email;
    private final String address;
    private OrderStatus orderStatus;
    private final List<OrderFood> orderFoods;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, String name, Email email, String address, OrderStatus orderStatus, List<OrderFood> orderFoods) {
        this.orderId = orderId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.orderStatus = orderStatus;
        this.orderFoods = orderFoods;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Order(UUID orderId, String name, Email email, String address, List<OrderFood> orderFoods) {
        this(orderId, name, email, address, OrderStatus.ORDERED, orderFoods);
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<OrderFood> getOrderFoods() {
        return orderFoods;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.updatedAt = LocalDateTime.now();
    }
}
