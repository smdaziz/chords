--create a new database for carshare application
create database carshare;

-- create tables
create table user (lastname varchar(255), 
firstname varchar(255), 
email varchar(255), 
mobile varchar(255), 
username varchar(255) primary key, 
password varchar(255));

create table ride (username varchar(255), 
source varchar(255), 
dest varchar(255), 
hh int, 
mm int, 
passengers int, 
srclat varchar(255), 
srclng varchar(255), 
destlat varchar(255), 
destlng varchar(255), 
ridedate date, 
constraint ride_un_fk foreign key(username) references user(username));

create table shareride (username varchar(255), 
rideby varchar(255), 
ridedate date, 
constraint shareride_un_fk foreign key(username) references user(username), 
constraint shareride_rb_fk foreign key(rideby) references ride(username));
