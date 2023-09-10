DROP TABLE IF EXISTS auction;

CREATE TABLE IF NOT EXISTS auction (
    id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    initial_price DOUBLE NOT NULL,
    current_price DOUBLE,
    description VARCHAR(5000),
    end_time DATETIME NOT NULL
);