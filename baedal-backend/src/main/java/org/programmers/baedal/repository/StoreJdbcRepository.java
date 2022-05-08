package org.programmers.baedal.repository;

import org.programmers.baedal.model.Category;
import org.programmers.baedal.model.Store;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static org.programmers.baedal.repository.JdbcUtils.toLocalDateTime;
import static org.programmers.baedal.repository.JdbcUtils.toUUID;

@Repository
public class StoreJdbcRepository implements StoreRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StoreJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Store> findAll() {
        return jdbcTemplate.query("select * from stores", storeRowMapper);
    }

    @Override
    public Store insert(Store store) {
        int update = jdbcTemplate.update("INSERT INTO stores(store_id, store_name, is_opened, category, address, delivery_fee, created_at, updated_at) " +
                "VALUES(UUID_TO_BIN(:storeId), :storeName, :isOpened, :category, :address, :deliveryFee, :createdAt, :updatedAt)", toParamMap(store));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return store;
    }

    @Override
    public Store update(Store store) {
        int update = jdbcTemplate.update(
                "UPDATE stores SET store_name = :storeName, is_opened = :isOpened, category = :category, address = :address, delivery_fee = :deliveryFee, created_at = :createdAt, updated_at = :updatedAt " +
                "WHERE store_id = UUID_TO_BIN(:storeId)", toParamMap(store));
        if (update != 1) {
            throw new RuntimeException("Nothing was updated");
        }
        return store;
    }

    @Override
    public Optional<Store> findById(UUID storeId) {
        try{
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM stores WHERE store_id = UUID_TO_BIN(:storeId)",
                            Collections.singletonMap("storeId", storeId.toString().getBytes()), storeRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Store> findByName(String storeName) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("SELECT * FROM stores WHERE store_name = :storeName",
                            Collections.singletonMap("storeName", storeName), storeRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Store> findByCategory(Category category) {
        return jdbcTemplate.query(
                "SELECT * FROM stores WHERE category = :category",
                Collections.singletonMap("category", category.toString()),
                storeRowMapper
        );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("DELETE FROM stores", Collections.emptyMap());
    }

    @Override
    public void deleteStoreById(UUID storeID) {
        jdbcTemplate.update("delete from stores where store_id = UUID_TO_BIN(:storeId)",
                Collections.singletonMap("storeId", storeID.toString().getBytes()));
    }


    private static final RowMapper<Store> storeRowMapper = (resultSet, i) -> {
        UUID storeId = toUUID(resultSet.getBytes("store_id"));
        String storeName =  resultSet.getString("store_name");
        boolean isOpened = resultSet.getBoolean("is_opened");
        Category category = Category.valueOf(resultSet.getString("category"));
        String address = resultSet.getString("address");
        long deliveryFee = resultSet.getLong("delivery_fee");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new Store(
                storeId,
                storeName,
                isOpened,
                category,
                address,
                deliveryFee,
                createdAt,
                updatedAt
        );
    };

    private Map<String, Object> toParamMap(Store store) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("storeId", store.getStoreId().toString().getBytes());
        paramMap.put("storeName", store.getName());
        paramMap.put("isOpened", store.isOpened());
        paramMap.put("category", store.getCategory().toString());
        paramMap.put("address", store.getAddress());
        paramMap.put("deliveryFee", store.getDeliveryFee());
        paramMap.put("createdAt", store.getCreatedAt());
        paramMap.put("updatedAt", store.getUpdatedAt());
        return paramMap;
    }
}
