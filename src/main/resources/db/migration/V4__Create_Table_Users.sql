CREATE TABLE users
(
    id                          UUID PRIMARY KEY,
    username                    VARCHAR(20)  NOT NULL UNIQUE,
    email                       VARCHAR(50)  NOT NULL UNIQUE,
    password                    VARCHAR(120) NOT NULL,
    account_non_expired         BOOLEAN      NOT NULL DEFAULT TRUE,
    account_non_locked          BOOLEAN      NOT NULL DEFAULT TRUE,
    credentials_non_expired     BOOLEAN      NOT NULL DEFAULT TRUE,
    enabled                     BOOLEAN      NOT NULL DEFAULT TRUE,
    credentials_non_expiry_date TIMESTAMP,
    account_expiry_date         TIMESTAMP,
    two_factor_secret           VARCHAR(255),
    two_factor_enabled          BOOLEAN      NOT NULL DEFAULT FALSE,
    sign_up_method              VARCHAR(255) NOT NULL,
    role_id                     UUID         NOT NULL,
    created_at                  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at                  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (id)
);