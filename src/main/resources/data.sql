INSERT INTO customers ( username, password, name, surname, is_account_non_expired, is_account_non_locked
                     , is_credentials_non_expired
                     , is_enabled)
VALUES ('abc', '$2a$10$gf/DoOCU1c.3trHuJGYQKOebxODx1pgYJS6n7DQvpIqXQ7kG5NgK.', 'abc', '34',0,0,0,1);

INSERT INTO products (name, author, price, quantity)
VALUES ('Vadideki Zambak', 'Honoré de Balzac', 1000.0, 34);

INSERT INTO products (name, author, price, quantity)
VALUES ('Sefiller', 'Victor Hugo', 500.0, 32);
