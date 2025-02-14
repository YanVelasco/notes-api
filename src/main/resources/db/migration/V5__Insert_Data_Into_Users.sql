INSERT INTO users (id, username, email, password, account_non_expired, account_non_locked, credentials_non_expired,
                   enabled, credentials_non_expiry_date, account_expiry_date, two_factor_enabled, sign_up_method,
                   role_id, created_at, updated_at)
VALUES (gen_random_uuid(), 'Yan', 'yan@example.com', '{noop}password', TRUE, TRUE, TRUE, TRUE,
        CURRENT_TIMESTAMP + INTERVAL '1 year', CURRENT_TIMESTAMP + INTERVAL '1 year', FALSE, 'email',
        (SELECT id FROM roles WHERE role_name = 'ROLE_ADMIN'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users (id, username, email, password, account_non_expired, account_non_locked, credentials_non_expired,
                   enabled, credentials_non_expiry_date, account_expiry_date, two_factor_enabled, sign_up_method,
                   role_id, created_at, updated_at)
VALUES (gen_random_uuid(), 'CommonUser', 'commonuser@example.com', '{noop}password', TRUE, TRUE, TRUE, TRUE,
        CURRENT_TIMESTAMP + INTERVAL '1 year', CURRENT_TIMESTAMP + INTERVAL '1 year', FALSE, 'email',
        (SELECT id FROM roles WHERE role_name = 'ROLE_USER'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);