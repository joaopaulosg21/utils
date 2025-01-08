CREATE TABLE IF NOT EXISTS monthly_expense(
    id SERIAL NOT NULL PRIMARY KEY,
    month VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);