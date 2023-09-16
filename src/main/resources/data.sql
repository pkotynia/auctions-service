INSERT INTO auction(name, initial_price, description, end_time) VALUES("Opla sprzedam", 1.0, "opel", "2023-09-10T09:25:12");
INSERT INTO auction(name, initial_price, description, end_time) VALUES("Sprzedam toyotę", 2.0, "toyota", "2023-09-10T09:25:12");

INSERT INTO seller(name) VALUES("Janusz");

INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,1);
INSERT INTO seller_auction(seller_id, auction_id) VALUES(1,2);

