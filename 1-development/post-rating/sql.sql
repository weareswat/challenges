create schema mydb;

create table mydb.Post(
id int not null auto_increment primary key,
up_votes int,
down_votes int);