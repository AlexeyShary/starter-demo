DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users
(
    user_id   BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    user_name VARCHAR NOT NULL,
    user_age  INTEGER NOT NULL
);