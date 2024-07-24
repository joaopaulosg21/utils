CREATE TABLE IF NOT EXISTS daily_task(
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    expiration_date TIMESTAMP NOT NULL,
    priority VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);