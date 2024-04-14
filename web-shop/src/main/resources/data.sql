INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,role,average_rating) VALUES ('Ana','Andric','ana123','ana@gmail.com','0664536780','ana111','2001-02-10','datoteka.jpg','bio','SELLER',10.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,role,average_rating) VALUES ('Maja','Majic','maja123','maja@gmail.com','0624536780','maja111','2002-02-10','datoteka.jpg','bio','BUYER',5.0);
INSERT INTO ACCOUNT (name,last_name,username,email,phone_number,password,date_of_birth,image_path,description,role,average_rating) VALUES ('Jovan','Jovic','jovan123','jovan@gmail.com','0654536780','jovan111','1999-02-10','datoteka.jpg','bio','ADMINISTRATOR',9.6);

INSERT INTO REPORT (reason,report_date,user_id,reporter_id) VALUES ('nesto','2024-04-12',1,2);
INSERT INTO REPORT (reason,report_date,user_id,reporter_id) VALUES ('nesto','2024-04-10',2,1);

INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (5,'komentar','2024-01-24',2);
INSERT INTO REVIEW (rating,comment,review_date,user_id) VALUES (3,'komentar','2024-01-04',1);