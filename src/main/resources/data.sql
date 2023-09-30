INSERT INTO category(name) VALUES("Moto");
INSERT INTO category(name) VALUES("Pets");
INSERT INTO category(name) VALUES("Health");
INSERT INTO category(name) VALUES("Tools");

INSERT INTO auction(name, initial_price, description, end_time, category_id) VALUES("Opla sprzedam", 1.0, "opel", "2023-09-10T09:25:12", 1);
INSERT INTO auction(name, initial_price, description, end_time, category_id) VALUES("Sprzedam toyotę", 2.0, "toyota", "2023-09-10T09:25:12", 1);

INSERT INTO seller(name) VALUES("Janusz");

INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,1);
INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,2);

INSERT INTO users VALUES (1, "user1", "password", 1);
INSERT INTO authorities VALUES (1, "user1", "USER")


