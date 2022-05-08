package org.programmers.baedal.service;

import org.programmers.baedal.model.Category;
import org.programmers.baedal.model.Store;
import org.programmers.baedal.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultStoreService implements StoreService {

    private final StoreRepository storeRepository;

    public DefaultStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public List<Store> getStoreByCategory(Category category) {
        return storeRepository.findByCategory(category);
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store createStore(String storeName, Category category, String address, long deliveryFee) {
        Store store = new Store(UUID.randomUUID(), storeName, category, address, deliveryFee);
        return storeRepository.insert(store);
    }

    @Override
    public Store createStore(String storeName, Category category, String address) {
        Store store = new Store(UUID.randomUUID(), storeName, category, address);
        return storeRepository.insert(store);
    }

    @Override
    public Store getStoreById(UUID storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        store.orElseThrow(() -> new RuntimeException("uuid가 존재하지 않습니다"));
        return store.get();
    }

    @Override
    public void deleteStoreByID(UUID storeId) {
        storeRepository.deleteStoreById(storeId);
    }
}
