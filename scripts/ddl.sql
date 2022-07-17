CREATE DATABASE ad_agency;

CREATE TYPE user_role AS ENUM ('ADMIN', 'CLIENT');

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2)
);

CREATE TABLE ad (
    id SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    file_name VARCHAR(100) NOT NULL,
    file_size BIGINT CHECK(file_size > 0 AND file_size < 32000000000),
    translation_time SMALLINT CHECK(translation_time >= 1 AND translation_time <= 24)
);

CREATE TABLE device (
    id SERIAL PRIMARY KEY,
    capacity BIGINT CHECK(capacity > 0 AND capacity <= 32000000000),
    state BOOLEAN
);

CREATE TABLE schedule (
    id SERIAL PRIMARY KEY,
    device_id BIGINT,
    FOREIGN KEY(device_id) REFERENCES device(id) ON DELETE CASCADE,
    ad_id BIGINT,
    FOREIGN KEY(ad_id) REFERENCES ad(id) ON DELETE CASCADE
);

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    FOREIGN KEY(user_id) REFERENCES "users"(id) ON DELETE CASCADE,
    ad_id BIGINT,
    FOREIGN KEY(ad_id) REFERENCES ad(id) ON DELETE CASCADE,
    order_time TIMESTAMP WITH TIME ZONE DEFAULT NOW(),
    price DECIMAL(10, 2),
    payment BOOLEAN
);
