package org.programmers.baedal.repository;

import org.programmers.baedal.model.Food;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

import static org.programmers.baedal.repository.JdbcUtils.toLocalDateTime;
import static org.programmers.baedal.repository.JdbcUtils.toUUID;

@Repository
public class FoodJdbcRepository implements FoodRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public FoodJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Food> findByStoreId(UUID storeId) {
        return jdbcTemplate.query("select * from foods where store_id = UUID_TO_BIN(:storeId)",
                Collections.singletonMap("storeId", storeId.toString().getBytes()), foodRowMapper);
    }

    @Override
    public Food insert(Food food) {
        int update = jdbcTemplate.update("insert into foods(food_id, store_id, food_name, price, description, created_at, updated_at) " +
                "values(UUID_TO_BIN(:foodId), UUID_TO_BIN(:storeId), :foodName, :price, :description, :createdAt, :updatedAt)",
                toParamMap(food));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return food;
    }

    @Override
    public Food update(Food food) {
        int update = jdbcTemplate.update("update foods set food_name = :foodName, price = :price, description = :description, created_at = :createdAt, updated_at = :updatedAt " +
                        "where food_id = UUID_TO_BIN(:foodId) and store_id = UUID_TO_BIN(:storeId)",
                        toParamMap(food));
        if (update != 1) {
            throw new RuntimeException("Nothing was inserted");
        }
        return food;
    }

    @Override
    public Optional<Food> findById(UUID foodId) {
        try{
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("select * from foods where food_id = UUID_TO_BIN(:foodId)",
                            Collections.singletonMap("foodId", foodId.toString().getBytes()), foodRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Food> findByNameAndStoreId(String foodName, UUID storeId) {
        try{
            Map paramMap = new HashMap<String, Object>();
            paramMap.put("storeId", storeId.toString().getBytes());
            paramMap.put("foodName", foodName);
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject("select * from foods where store_id = UUID_TO_BIN(:storeId) and food_name = :foodName",
                            paramMap, foodRowMapper)
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByFoodId(UUID foodId) {
        jdbcTemplate.update("delete from foods where food_id = UUID_TO_BIN(:foodId)", Collections.singletonMap("foodId", foodId.toString().getBytes()));
    }

    private static final RowMapper<Food> foodRowMapper = (resultSet, i) -> {
        UUID foodId = toUUID(resultSet.getBytes("food_id"));
        UUID storeId = toUUID(resultSet.getBytes("store_id"));
        String foodName =  resultSet.getString("food_name");
        long price = resultSet.getLong("price");
        String description = resultSet.getString("description");
        LocalDateTime createdAt = toLocalDateTime(resultSet.getTimestamp("created_at"));
        LocalDateTime updatedAt = toLocalDateTime(resultSet.getTimestamp("updated_at"));

        return new Food(
                foodId,
                storeId,
                foodName,
                price,
                description,
                createdAt,
                updatedAt
        );
    };

    private Map<String, Object> toParamMap(Food food) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("foodId", food.getFoodId().toString().getBytes());
        paramMap.put("storeId", food.getStoreId().toString().getBytes());
        paramMap.put("foodName", food.getName());
        paramMap.put("price", food.getPrice());
        paramMap.put("description", food.getDescription());
        paramMap.put("createdAt", food.getCreatedAt());
        paramMap.put("updatedAt", food.getUpdatedAt());
        return paramMap;
    }
}
