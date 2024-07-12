CREATE TABLE "user" (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(32),
    creation_date DATE
);

CREATE TYPE ticket_type AS ENUM ('DAY', 'WEEK', 'MONTH', 'YEAR');

CREATE TABLE "ticket" (
    id SERIAL PRIMARY KEY ,
    ticket_type ticket_type,
    creation_date DATE,
    user_id INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE
);