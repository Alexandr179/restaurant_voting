DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM menus;
DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', '{noop}password'),
('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
('USER', 100000),

('ADMIN', 100001),
('USER', 100001);

INSERT INTO restaurants (name, user_id) VALUES
('Теремок', 100000),
('Европа', 100001);

INSERT INTO menus (name, price, restaurant_id) VALUES
('суп томаты-фасоль', 300, 100002),
('паста', 400, 100003);