DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS restaurants CASCADE;
DROP TABLE IF EXISTS menus CASCADE;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name            VARCHAR                            NOT NULL,
    email           VARCHAR                            NOT NULL,
    password        VARCHAR                            NOT NULL,
    createVotingTime TIMESTAMP          DEFAULT now()  NOT NULL,
    enabled         BOOL                DEFAULT TRUE   NOT NULL,
    restaurant_id_voting       INTEGER             DEFAULT 0
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE user_roles
(
    user_id         INTEGER                             NOT NULL,
    role            VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE restaurants
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id         INTEGER                             ,
    name            VARCHAR                             NOT NULL,
    address         VARCHAR,
    phone           VARCHAR,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX restaurants_unique_phone_idx ON restaurants (id, phone);


CREATE TABLE menus
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id   INTEGER                             ,
    name            VARCHAR                             NOT NULL,
    price           INT                                 NOT NULL,

    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);