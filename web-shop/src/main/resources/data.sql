
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Ana','Andric','ana123','Ana.Andric@gmail.com','0664536780','ana111','2001-02-10','datoteka.jpg','bio','SELLER',10.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Maja','Majic','maja123','Maja.Majic@gmail.com','0624536780','maja111','2002-02-10','datoteka.jpg','bio','CUSTOMER',5.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,user_role,average_rating) VALUES ('Jovan','Jovic','jovan123','Jovan.Jovic@gmail.com','0654536780','jovan111','1999-02-10','datoteka.jpg','bio','ADMINISTRATOR',9.6);


INSERT INTO OFFER (price_offer, role)VALUES (20.00, 'CUSTOMER');

INSERT INTO OFFER (price_offer, role)VALUES (30.00, 'SELLER');

--INSERT INTO CATEGORY (category_name) VALUES ('ime kategorije')


INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (5,'komentar','2024-01-24',2);
INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (3,'komentar','2024-01-04',1);

--INSERT INTO REPORT (reason,report_date,user_id,reporter_id) VALUES ('nesto','2024-04-12',1,2);
--INSERT INTO REPORT (reason,report_date,user_id,reporter_id) VALUES ('nesto','2024-04-10',2,1);

