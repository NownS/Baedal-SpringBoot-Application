package org.programmers.baedal.service;

import org.programmers.baedal.model.Category;
import org.programmers.baedal.model.Store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreService {
    List<Store> getStoreByCategory(Category category);
    List<Store> getAllStores();
    Store createStore(String storeName, Category category, String address, long deliveryFee);
    Store createStore(String storeName, Category category, String address);
    Store getStoreById(UUID storeId);

    void deleteStoreByID(UUID storeId);
}
