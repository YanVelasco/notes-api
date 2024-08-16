CREATE TABLE users
(
    id         VARCHAR(255) PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) UNIQUE,
    password   TEXT                NOT NULL,
    avatar_url TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);