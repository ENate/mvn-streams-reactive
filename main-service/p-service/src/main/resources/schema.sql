
CREATE TABLE IF NOT EXISTS book (
    id bigint,
    title TEXT NOT NULL,
    isbn TEXT NOT NULL,
    author TEXT NOT NULL,
    price float NOT NULL
);