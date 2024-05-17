INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Ana','Andric','ana123','ana@gmail.com','0664536780','ana111','2001-02-10','datoteka.jpg','bio','SELLER',10.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Maja','Majic','maja123','maja@gmail.com','0624536780','maja111','2002-02-10','datoteka.jpg','bio','CUSTOMER',5.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Jovan','Jovic','jovan123','jovan@gmail.com','0654536780','jovan111','1999-02-10','datoteka.jpg','bio','ADMINISTRATOR',9.6);

INSERT INTO OFFER (price_offer, role)VALUES (20.00, 'CUSTOMER'),(40.00, 'ADMINISTRATOR'),(30.00, 'SELLER');

INSERT INTO CATEGORY(category_name)VALUES ('prva kategorija'),('druga kategorija'),('treca kategorija');

INSERT INTO PRODUCT (name, description, image_path, price, sales_type, release_date, category_id, customer_review, seller_review, is_sold, product_type)
VALUES
    ('Product1', 'Description for Product1', 'slika1.jpg', 100.0, 'FIXED_PRICE', '2024-01-01', 1, TRUE, FALSE, FALSE, 'FOR_SALE'),
    ('Product2', 'Description for Product2', 'slika2.jpg', 200.0, 'AUCTION', '2024-02-01', 2, FALSE, TRUE, TRUE, 'PURCHASED'),
    ('Product3', 'Description for Product3', 'slika3.jpg', 150.0, 'FIXED_PRICE', '2024-03-01', 3, TRUE, TRUE, FALSE, 'FOR_SALE');

INSERT INTO PRODUCT_OFFERS (product_id, offer_id) VALUES (1, 1),(3,2),(2,3);

