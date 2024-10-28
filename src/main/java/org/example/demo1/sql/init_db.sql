CREATE TABLE BOOKS
(
    id serial PRIMARY KEY,
    isbn varchar UNIQUE NOT NULL,
    title varchar NOT NULL,
    author varchar,
    published_date date
);

CREATE TABLE READERS
(
    id serial PRIMARY KEY,
    name varchar NOT NULL,
    email varchar UNIQUE NOT NULL
);
