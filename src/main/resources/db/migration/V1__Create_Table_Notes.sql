CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE notes (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    content VARCHAR(5000) NOT NULL,
    owner_username VARCHAR(255) NOT NULL
);