package org.programmers.baedal.controller;

import org.programmers.baedal.model.Food;
import org.programmers.baedal.model.Order;
import org.programmers.baedal.model.OrderFood;
import org.programmers.baedal.model.Store;
import org.programmers.baedal.service.OrderService;
import org.programmers.baedal.service.StoreFoodService;
import org.programmers.baedal.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {

    private final StoreService storeService;
    private final StoreFoodService storeFoodService;
    private final OrderService orderService;

    public OrderController(StoreService storeService, StoreFoodService storeFoodService, OrderService orderService) {
        this.storeService = storeService;
        this.storeFoodService = storeFoodService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String orderPage(Model model) {
        List<Store> stores = storeService.getAllStores();
        model.addAttribute("stores", stores);
        return "order-first-page";
    }

    @GetMapping("/orders/{id}")
    public String orderFoodPage(Model model, @PathVariable String id) {
        UUID storeId = UUID.fromString(id);
        Store store = storeService.getStoreById(storeId);
        List<Food> foods = storeFoodService.getAllFoodsByStoreId(storeId);
        model.addAttribute("store", store);
        model.addAttribute("foods", foods);
        return "order-second-page";
    }

    @GetMapping("/orders/{id1}/{id2}")
    public String orderFood(Model model, @PathVariable String id1, @PathVariable String id2) {
        UUID storeId = UUID.fromString(id1);
        UUID foodId = UUID.fromString(id2);
        Order order = orderService.createOrder(storeId, foodId);
        model.addAttribute("order", order);
        List<OrderFood> orderFoods = orderService.getOrderFoodsByOrderId(order.getOrderId());
        model.addAttribute("orderFoods", orderFoods);
        return "order-end-page";
    }
}
