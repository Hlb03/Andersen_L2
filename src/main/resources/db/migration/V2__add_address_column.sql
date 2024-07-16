CREATE TABLE "address"
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(32) NOT NULL,
    user_id INT         NOT NULL REFERENCES "user" (id) ON DELETE CASCADE
);