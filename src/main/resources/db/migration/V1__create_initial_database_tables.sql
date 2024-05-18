CREATE TABLE client_order (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       cpf VARCHAR(11) NULL,
                       status VARCHAR(255) NOT NULL,
                       payment_id VARCHAR(40) NULL,
                       order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       last_update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL

);

CREATE TABLE order_item (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            order_id INT NOT NULL,
                            item_id INT NOT NULL,
                            quantity INT NOT NULL,
                            total_price DECIMAL(10, 2) NOT NULL,
                            FOREIGN KEY (order_id) REFERENCES client_order(id)
);