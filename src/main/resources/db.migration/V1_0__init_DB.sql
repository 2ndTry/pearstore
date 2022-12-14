-- DROP CONSTRAINTS
ALTER TABLE if EXISTS card DROP CONSTRAINT if EXISTS card_fk_products;
ALTER TABLE if EXISTS card DROP CONSTRAINT if EXISTS card_fk_users;
ALTER TABLE if EXISTS products DROP CONSTRAINT if EXISTS products_fk_categories;
ALTER TABLE if EXISTS token DROP CONSTRAINT if EXISTS token_fk_users;
ALTER TABLE if EXISTS wishlist DROP CONSTRAINT if EXISTS wishlist_fk_products;
ALTER TABLE if EXISTS wishlist DROP CONSTRAINT if EXISTS wishlist_fk_users;

-- DROP TABLES
DROP TABLE if EXISTS card CASCADE;
DROP TABLE if EXISTS categories CASCADE;
DROP TABLE if EXISTS products CASCADE;
DROP TABLE if EXISTS token CASCADE;
DROP TABLE if EXISTS users CASCADE;
DROP TABLE if EXISTS wishlist CASCADE;

-- DROP HIBY SEQ
DROP sequence if EXISTS hibernate_sequence;

-- CREATE HIBY SEQ
CREATE sequence hibernate_sequence start 1 increment 1;

-- CREATE TABLES
CREATE TABLE card (
    id int8 NOT NULL,
    created_date TIMESTAMP,
    quantity int4 NOT NULL,
    product_id int8,
    user_id int8,
    PRIMARY KEY (id)
);
CREATE TABLE categories (
    id int8 generated BY DEFAULT AS IDENTITY,
    category_name VARCHAR(255),
    description VARCHAR(255),
    image_url VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE products (
    id int8 NOT NULL,
    description VARCHAR(255),
    image_url VARCHAR(255),
    name VARCHAR(255),
    price float8 NOT NULL,
    category_id int8,
    PRIMARY KEY (id)
);
CREATE TABLE token (
    id int8 NOT NULL,
    created_date TIMESTAMP,
    token VARCHAR(255),
    user_id int8 NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE users (
    id int8 NOT NULL,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE wishlist (
    id int8 NOT NULL,
    created_date TIMESTAMP,
    product_id int8,
    user_id int8,
    PRIMARY KEY (id)
);

-- ADD CONSTRAINTS
ALTER TABLE if EXISTS card ADD CONSTRAINT card_fk_products FOREIGN KEY (product_id) REFERENCES products;
ALTER TABLE if EXISTS card ADD CONSTRAINT card_fk_users FOREIGN KEY (user_id) REFERENCES users;
ALTER TABLE if EXISTS products ADD CONSTRAINT products_fk_categories FOREIGN KEY (category_id) REFERENCES categories;
ALTER TABLE if EXISTS token ADD CONSTRAINT token_fk_users FOREIGN KEY (user_id) REFERENCES users;
ALTER TABLE if EXISTS wishlist ADD CONSTRAINT wishlist_fk_products FOREIGN KEY (product_id) REFERENCES products;
ALTER TABLE if EXISTS wishlist ADD CONSTRAINT wishlist_fk_users FOREIGN KEY (user_id) REFERENCES users;