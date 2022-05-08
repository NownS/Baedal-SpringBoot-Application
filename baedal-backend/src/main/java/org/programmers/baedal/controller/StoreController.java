package org.programmers.baedal.controller;

import org.programmers.baedal.dto.CreateStoreRequest;
import org.programmers.baedal.model.Category;
import org.programmers.baedal.model.Store;
import org.programmers.baedal.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public String storesPage(Model model) {
        List<Store> stores = storeService.getAllStores();
        model.addAttribute("stores", stores);
        return "store-list";
    }

    @GetMapping("/new-store")
    public String newStorePage(Model model) {
        Class categoryType = Category.class;
        model.addAttribute("categories",categoryType.getEnumConstants());
        return "new-store";
    }

    @PostMapping("/new-store")
    public String newStore(CreateStoreRequest createStoreRequest) {
        storeService.createStore(
            createStoreRequest.getName(),
            createStoreRequest.getCategory(),
            createStoreRequest.getAddress(),
            createStoreRequest.getDeliveryFee()
        );
        return "redirect:/stores";
    }

    @GetMapping("/{id}/delete")
    public String deleteStore(@PathVariable String id) {
        storeService.deleteStoreByID(UUID.fromString(id));
        return "redirect:/stores";
    }
}
