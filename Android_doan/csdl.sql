

Create TABLE Users (
    ID INTEGER  PRIMARY KEY AUTOINCREMENT,
    UserName varchar(255),
    Email varchar(255),
    Phone varchar(255),
    Adress varchar(255),
    Pass varchar(255),
	Level varchar(10)
);

drop table Users
select * from Users

INSERT INTO Users( UserName, Email, Phone,Pass,Adress ,Level)
VALUES ( 'dat', 'dat@gmail.com' , '086665235' ,'1' ,'', '0'),
        ( 'tien', 'tien@gmail.com' , '086665295' ,'2' ,'', '0');
        
        

INSERT INTO Users( UserName, Email, Phone,Pass,Level)
VALUES ( 'dat2', 'datbe26092001@gmail.com' , '086665235' ,' 123456' , '1'),

sELECT * FROM Users where Email = 'dat@gmail.com' and Pass =' 1'
SELECT * FROM Users where Email = 'datbe26092001@gmail.com' and Pass ='123456'