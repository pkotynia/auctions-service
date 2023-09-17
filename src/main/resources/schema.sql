DROP TABLE IF EXISTS auction;
DROP TABLE IF EXISTS category;

CREATE TABLE IF NOT EXISTS category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS auction (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    initial_price DECIMAL(6, 2) NOT NULL,
    current_price DECIMAL(6, 2),
    description VARCHAR(5000),
    end_time DATETIME NOT NULL,
    category_id INT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

DROP TABLE IF EXISTS seller;

CREATE TABLE IF NOT EXISTS seller (
    seller_id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS seller_auction;
CREATE TABLE IF NOT EXISTS seller_auction (
    seller_id INT,
    auction_id INT
);



