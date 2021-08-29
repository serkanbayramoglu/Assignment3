insert into admin(id,username,password,firstname,lastname) values (10001,'admin','pass','John','Smith');

insert into users(id,username,password,firstname,lastname,datesubscribed) values (10001,'pholley','pass1','Peter','Holley',sysdate());
insert into users(id,username,password,firstname,lastname,datesubscribed) values (10002,'hbarth','pass2','Hans','Barth',sysdate());
insert into users(id,username,password,firstname,lastname,datesubscribed) values (10003,'lcampbel','pass3','Liz','Campbel',sysdate());
insert into users(id,username,password,firstname,lastname,datesubscribed) values (10004,'sgupta','pass4','Sunil','Gupta',sysdate());
insert into users(id,username,password,firstname,lastname,datesubscribed) values (10005,'alynn','pass5','Ann','Lynn',sysdate());
insert into users(id,username,password,firstname,lastname,datesubscribed) values (10006,'agupta','pass6','Ann','Gupta',sysdate());	

insert into brands(id,brandname) values (10001,'Adidas');	
insert into brands(id,brandname) values (10002,'Nike');	
insert into brands(id,brandname) values (10003,'Puma');	

insert into categories(id,categoryname) values (10001,'Tennis');	
insert into categories(id,categoryname) values (10002,'Basketball');	
insert into categories(id,categoryname) values (10003,'Football');	
insert into categories(id,categoryname) values (10004,'Kids');	
insert into categories(id,categoryname) values (10005,'Running');

insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10001,'A01',99,10001,10001,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10002,'A02',89,10003,10001,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10003,'A03',69,10004,10001,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10004,'B01',74,10003,10002,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10005,'B02',64,10005,10002,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10006,'B03',84,10004,10002,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10007,'C01',89,10001,10003,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10008,'C02',95,10002,10003,true);	
insert into shoes(id,serialno,currentprice,category_id,brand_id,displayshoe) values (10009,'C03',55,10005,10003,true);	

insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10001,'F',99,sysdate(),30,10002,10001);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10002,'M',99,sysdate(),44,10001,10002);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10003,'F',74,sysdate(),33,10004,10002);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10004,'M',64,sysdate(),36,10005,10001);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10005,'M',69,sysdate(),24,10003,10005);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10006,'F',99,sysdate(),38,10001,10004);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10007,'F',69,sysdate(),26,10003,10004);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10008,'F',84,sysdate(),22,10006,10002);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10009,'M',74,sysdate(),39,10004,10001);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10010,'M',99,sysdate(),42,10001,10003);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10011,'M',55,sysdate(),34,10009,10004);	
insert into purchases(id,gender,saleprice,saledate,size,product_id,user_id) values (10012,'F',55,sysdate(),41,10009,10005);	


