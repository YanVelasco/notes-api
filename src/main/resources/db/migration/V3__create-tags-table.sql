CREATE TABLE IF NOT EXISTS tags
(
    id       VARCHAR(255) PRIMARY KEY,
    tag_name VARCHAR(255) NOT NULL,
    user_id  VARCHAR(255) NOT NULL,
    CONSTRAINT fk_user
    FOREIGN KEY (user_id) REFERENCES users(id)
);