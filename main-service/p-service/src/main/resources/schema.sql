CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE IF NOT EXISTS book (
    id TEXT NOT NULL,
    title TEXT NOT NULL,
    isbn TEXT NOT NULL,
    author TEXT NOT NULL,
    price float NOT NULL
);