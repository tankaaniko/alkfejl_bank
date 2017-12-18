insert into employee(name,username,password) values ('AA','aa','aa');
insert into employee(name,username,password) values ('BB','bb','bb');
insert into employee(name,username,password) values ('CC','cc','cc');
insert into employee(name,username,password) values ('DD','dd','dd');



insert into clients(name,username,password,pin) values ( 'A' , 'a','a',0000);
insert into clients(name,username,password,pin) values ( 'B' , 'b','b',0001);
insert into clients(name,username,password,pin) values ( 'C' , 'c','c',0002);
insert into clients(name,username,password,pin) values ( 'D' , 'd','d',0003);
insert into clients(name,username,password,pin) values ( 'E' , 'e','e',0004);


insert into account(username, account_number, balance,blocked,creation_date,client_id) values ('a' ,'1111',1000,false,to_date('2017.11.06.','YYYY.MM.DD.'),1);
insert into account(username, account_number, balance,blocked,creation_date,client_id) values ('b' ,'2222',1000,false,to_date('2017.11.05.','YYYY.MM.DD.'),2);
insert into account(username, account_number, balance,blocked,creation_date,client_id) values ('b' ,'3333',1000,false,to_date('2017.11.04.','YYYY.MM.DD.'),3);
insert into account(username, account_number, balance,blocked,creation_date,client_id) values ('b' ,'4444',1000,false,to_date('2017.11.03.','YYYY.MM.DD.'),4);
insert into account(username, account_number, balance,blocked,creation_date,client_id) values ('a' ,'5555',1000,false,to_date('2017.11.02.','YYYY.MM.DD.'),5);


insert into transaction(amount,date,source_account_number,target_account_number,target_client_name,type,status) values (20,current_timestamp,'1111','3333','B','TRANSFER','ACTIVE');
insert into transaction(amount,date,source_account_number,target_account_number,target_client_name,type,status) values (20,current_timestamp,'2222','5555','D','TRANSFER','ACTIVE');
