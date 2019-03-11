CREATE TABLE items(
    id SERIAL,
    "name" VARCHAR,
    code VARCHAR,
    description TEXT,
    base_fee DECIMAL(25,2),
    active BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);