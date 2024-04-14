INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Ana','Andric','ana123','ana@gmail.com','0664536780','ana111','2001-02-10','datoteka.jpg','bio','SELLER',10.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Maja','Majic','maja123','maja@gmail.com','0624536780','maja111','2002-02-10','datoteka.jpg','bio','CUSTOMER',5.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Jovan','Jovic','jovan123','jovan@gmail.com','0654536780','jovan111','1999-02-10','datoteka.jpg','bio','ADMINISTRATOR',9.6);

--INSERT INTO REPORT_USER (reason,report_date,user_id,reporter_id,status) VALUES ('nesto','2024-04-12',1,2,'SUBMITTED');
--INSERT INTO REPORT_USER (reason,report_date,user_id,reporter_id,status) VALUES ('nesto','2024-04-10',2,1,'REJECTED');

--INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (5,'komentar','2024-01-24',2);
--INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (3,'komentar','2024-01-04',1);

INSERT INTO CATEGORY (category_name)VALUES ('Kategorija 1');

INSERT INTO CATEGORY (category_name)VALUES ('Kategorija 2');

INSERT INTO OFFER (price_offer, role)VALUES (20.00, 'CUSTOMER');

INSERT INTO OFFER (price_offer, role)VALUES (30.00, 'SELLER');

INSERT INTO PRODUCT (name, description, image_path, price, sales_type, release_date, category_id, customer_review, seller_review, is_sold, product_type)
VALUES ('Proizvod 1', 'Opis proizvoda 1', 'slika1.jpg', 25.99, 'FIXED_PRICE', '2024-04-15', 1, true, false, false, 'FOR_SALE');

INSERT INTO PRODUCT (name, description, image_path, price, sales_type, release_date, category_id, customer_review, seller_review, is_sold, product_type)
VALUES ('Proizvod 2', 'Opis proizvoda 2', 'slika2.jpg', 35.50, 'FIXED_PRICE', '2024-04-16', 2, true, false, false, 'FOR_SALE');