package org.programmers.baedal.controller;

import org.programmers.baedal.dto.CreateFoodRequest;
import org.programmers.baedal.model.Food;
import org.programmers.baedal.model.Store;
import org.programmers.baedal.service.StoreFoodService;
import org.programmers.baedal.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class StoreFoodController {

    private final StoreFoodService storeFoodService;
    private final StoreService storeService;

    public StoreFoodController(StoreFoodService storeFoodService, StoreService storeService) {
        this.storeFoodService = storeFoodService;
        this.storeService = storeService;
    }

    @GetMapping("/{id}/list")
    public String storeFoodsPage(Model model, @PathVariable String id) {
        UUID storeId = UUID.fromString(id);
        Store store = storeService.getStoreById(storeId);
        List<Food> foods = storeFoodService.getAllFoodsByStoreId(storeId);
        model.addAttribute("store", store);
        model.addAttribute("foods", foods);
        return "food-list";
    }

    @GetMapping("/{id}/new-food")
    public String newFoodsPage(Model model, @PathVariable String id) {
        UUID storeId = UUID.fromString(id);
        Store store = storeService.getStoreById(storeId);
        model.addAttribute(store);
        return "new-food";
    }

    @PostMapping("/{id}/new-food")
    public String newFood(CreateFoodRequest createFoodRequest, @PathVariable String id) {
        UUID storeId = UUID.fromString(id);
        storeFoodService.createFood(
                storeId,
                createFoodRequest.getFoodName(),
                createFoodRequest.getPrice(),
                createFoodRequest.getDescription()
                );
        return "redirect:/{id}/list";
    }

    @GetMapping("/{id1}/{id2}/delete")
    public String deleteFood(@PathVariable String id1, @PathVariable String id2) {
        UUID foodId = UUID.fromString(id2);
        storeFoodService.deleteFoodById(foodId);
        return "redirect:/{id1}/list";
    }
}
