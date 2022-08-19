CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS book (
    id uuid default uuid_generate_v4(),
    title TEXT NOT NULL,
    isbn TEXT NOT NULL,
    author bigint NOT NULL,
    price bigint NOT NULL
);