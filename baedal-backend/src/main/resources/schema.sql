CREATE TABLE stores
(
    store_id binary(16) PRIMARY KEY,
    store_name VARCHAR(20) NOT NULL,
    is_opened boolean NOT NULL,
    category VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    delivery_fee bigint NOT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL
)

CREATE TABLE foods
(
    food_id binary(16) PRIMARY KEY,
    store_id binary(16) NOT NULL,
    food_name VARCHAR(20) NOT NULL,
    price bigint NOT NULL,
    description VARCHAR(500) DEFAULT NULL,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL,
    CONSTRAINT fk_foods_to_store FOREIGN KEY (store_id) REFERENCES stores(store_id) ON DELETE CASCADE
)

CREATE TABLE orders
(
    order_id binary(16) PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    email VARCHAR(50) NOT NULL,
    address VARCHAR(200) NOT NULL,
    order_status VARCHAR(50),
    created_at datetime(6) NOT NULL,
    updated_at datetime(6) DEFAULT NULL,
)

CREATE TABLE order_foods
(
    seq bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id binary(16) NOT NULL,
    store_id binary(16) NOT NULL,
    food_id binary(16) NOT NULL,
    price bigint NOT NULL,
    quantity int NOT NULL,
    CONSTRAINT fk_order_foods_to_order FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    CONSTRAINT fk_order_foods_to_food FOREIGN KEY (food_id) REFERENCES foods(food_id),
    CONSTRAINT fk_order_foods_to_store FOREIGN KEY (store_id) REFERENCES stores(store_id)
)