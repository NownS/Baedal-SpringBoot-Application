package org.programmers.baedal.repository;

import org.programmers.baedal.model.Category;
import org.programmers.baedal.model.Store;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StoreRepository {

    List<Store> findAll();
    Store insert(Store store);
    Store update(Store store);
    Optional<Store> findById(UUID storeId);
    Optional<Store> findByName(String storeName);
    List<Store> findByCategory(Category category);
    void deleteAll();

    void deleteStoreById(UUID storeID);

}
