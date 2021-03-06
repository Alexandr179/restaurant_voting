DROP TABLE user_roles IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP TABLE menus IF EXISTS;
DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    name            VARCHAR(255)                            NOT NULL,
    email           VARCHAR(255)                            NOT NULL,
    password        VARCHAR(255)                            NOT NULL,
    createVotingTime TIMESTAMP             DEFAULT now()    NOT NULL,
    enabled         BOOLEAN                DEFAULT TRUE     NOT NULL,
    restaurant_id_voting       INTEGER DEFAULT 0    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);


CREATE TABLE user_roles
(
    user_id         INTEGER                                 NOT NULL,
    role            VARCHAR(255),
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    user_id         INTEGER                                 ,
    name            VARCHAR(255)                            NOT NULL,
    address         VARCHAR(255),
    phone           VARCHAR(255),

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX restaurants_unique_phone_idx ON restaurants (id, phone);

CREATE TABLE menus
(
    id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
    restaurant_id   INTEGER                             ,
    name            VARCHAR(255)                            NOT NULL,
    price           INT                                     NOT NULL,

    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);