DROP TABLE IF EXISTS auction;

CREATE TABLE IF NOT EXISTS auction (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    initial_price DOUBLE NOT NULL,
    current_price DOUBLE,
    description VARCHAR(5000),
    end_time DATETIME NOT NULL
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

