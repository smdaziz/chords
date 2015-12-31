CREATE DATABASE retail_db;

USE retail_db;

CREATE TABLE Product (
  productID varchar(255) NOT NULL,
  name varchar(255) DEFAULT NULL,
  supplier varchar(255) DEFAULT NULL,
  productType varchar(255) DEFAULT NULL,
  unitPrice double DEFAULT NULL,
  availableQuantity int(11) DEFAULT NULL,
  status varchar(255) DEFAULT NULL,
  PRIMARY KEY (productID)
);

insert into product values('P1001', 'Sony Vaio', 'Sony', 'Electronic', 725, 34, 'Not Available');
insert into product values('P1002', 'Duffle Bag', 'American Tourister', 'Travel', 65, 25, 'Available');
insert into product values('P1003', 'Pepe Jeans', 'Pepe', 'Clothes', 18, 105, 'Available');

CREATE TABLE Customer (
  customerID varchar(255) NOT NULL,
  name varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  phone1 varchar(255) DEFAULT NULL,
  phone2 varchar(255) DEFAULT NULL,
  PRIMARY KEY (customerID)
);

insert into customer values('C1001', 'ABC Inc', '112 A, Long View, CA', 1112223333, 1112223334);
insert into customer values('C1002', 'XYZ Ltd', '587, Dakota Drive, ND', 1112224444, 1112224445);

CREATE TABLE SalesOrder (
  orderID varchar(255) NOT NULL,
  customerID varchar(255) DEFAULT NULL,
  totalPrice double DEFAULT NULL,
  PRIMARY KEY (orderID),
  KEY FK_cust_id (customerID),
  CONSTRAINT FK_cust_id FOREIGN KEY (customerID) REFERENCES Customer (customerID)
);

insert into salesorder values('SO1001', 'C1001', 920);
insert into salesorder values('SO1002', 'C1002', 992);

CREATE TABLE OrderItem (
  orderItemID int(11),
  productID varchar(255) DEFAULT NULL,
  quantity int(11) DEFAULT NULL,
  totalPrice double DEFAULT NULL,
  orderID varchar(255) DEFAULT NULL
);

insert into orderitem values(1, 'P1001', 1, 725, 'SO1001');
insert into orderitem values(2, 'P1002', 3, 195, 'SO1001');
insert into orderitem values(3, 'P1001', 1, 725, 'SO1002');
insert into orderitem values(4, 'P1002', 3, 195, 'SO1002');
insert into orderitem values(5, 'P1003', 4, 72, 'SO1002');

commit;

