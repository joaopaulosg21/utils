CREATE TABLE IF NOT EXISTS shopping_list (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS shopping_list_items (
    name VARCHAR(255) NOT NULL,
    quantity VARCHAR(100) NOT NULL,
    shopping_list_id INT NOT NULL,
    FOREIGN KEY(shopping_list_id) REFERENCES shopping_list(id)
);
