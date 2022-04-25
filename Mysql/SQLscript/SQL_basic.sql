create database web;
use web;
show tables;
create table books (
book_code int not null auto_increment primary key,
title varchar(50),
author varchar(30),
year varchar(10),
price int);

insert into books(title,author,year,price) values ('운영체제','김필동','2018','30000');
desc books;
select * from books;
delete from books;
select * from books;
insert into books(title,author,year,price) values ('자바','이현우','2019','35000');
insert into books(title,author,year,price) values ('c언어','박한빛','2020','40000');
set sql_safe_updates=0;
alter table books auto_increment=1;
delete from books where price>30000;

create table customer (
id char(20)primary key,
name varchar(30) not null,
age int not null,
grade varchar(20),
job varchar(30),
point int);

show tables;
describe customer;
insert into customer values ('apple','김현준',20,'gold','학생',1000);
insert into customer values ('banana','정소화',25,'vip','학생',2500);
insert into customer values ('carrot','원유선',28,'gold','학생',4500);
insert into customer values ('orange','정지영',22,'silver','학생',0);

select * from customer where grade='gold' and point >=2000;
select name,grade,point from customer ; -- 순수 관계 연산자 프로젝트
select grade distinct from customer;