DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orderDetails;


CREATE TABLE customers
(
    id          NUMERIC(22) AUTO_INCREMENT PRIMARY KEY,
    username       VARCHAR(250)  NOT NULL,
    password    VARCHAR(4000) NOT NULL,
    name        VARCHAR(250)  NOT NULL,
    surname     VARCHAR(250)  NOT NULL,
    is_account_non_expired int not null ,
    is_account_non_locked int not null ,
    is_credentials_non_expired int not null ,
    is_enabled int not null ,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE products
(
    id       NUMERIC(22) AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(250)   NOT NULL,
    author   VARCHAR(250) NOT NULL,
    price    NUMERIC(22, 2) NOT NULL,
    quantity NUMERIC(22)   NOT NULL
);


CREATE TABLE orders
(
    id          NUMERIC(22) AUTO_INCREMENT PRIMARY KEY,
    orderTime   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    orderStatus VARCHAR(250)       DEFAULT NULL,
    customer_id int       NOT NULL
);


CREATE TABLE orderDetails
(
    id          NUMERIC(22) AUTO_INCREMENT PRIMARY KEY,
    quantity    NUMERIC(22) NOT NULL ,
    order_id    NUMERIC(22) NOT NULL ,
    customer_id NUMERIC(22) NOT NULL
);