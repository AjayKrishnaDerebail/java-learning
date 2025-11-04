-- Create the product table
CREATE TABLE IF NOT EXISTS product
(
    product_id
    INT
    PRIMARY
    KEY,
    product_name
    VARCHAR
(
    100
) NOT NULL,
    product_category VARCHAR
(
    50
) NOT NULL,
    product_price DECIMAL
(
    10,
    2
) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

-- Insert the data
INSERT INTO products (product_id, product_name, product_category, product_price)
VALUES (1, 'Google Pixel 9', 'Smartphones', 94500.00),
       (2, 'Google Pixel 9 XL', 'Smartphones', 104500.00),
       (3, 'Google Pixel 9 Pro', 'Smartphones', 114500.00),
       (4, 'Google Pixel 9 Pro Max', 'Smartphones', 124500.00),
       (5, 'LG 4K Ultra HD Smart TV', 'Televisions', 149500.00),
       (6, 'Panasonic 4K Ultra HD Smart TV', 'Televisions', 159500.00);