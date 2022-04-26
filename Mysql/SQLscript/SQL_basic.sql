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
select distinct grade from customer;

CREATE TABLE 고객(
고객아이디 varchar(20) NOT NULL,
고객이름 varchar(10) NOT NULL,
나이 INT,
등급 VARCHAR(10) NOT NULL,
직업 VARCHAR(20),
적립금 INT DEFAULT 0,
PRIMARY KEY(고객아이디)
);

DESC 고객;

CREATE TABLE 제품(
제품번호 char(3) NOT NULL,
제품명 varchar(20),
재고량 INT,
단가 INT,
제조업체 VARCHAR(20),
PRIMARY KEY(제품번호),
CHECK (재고량>=0 AND 재고량<=10000)
);

DESC 제품;

CREATE TABLE 주문(
주문번호 char(3) NOT NULL,
주문고객 varchar(20),
주문제품 CHAR(3),
수량 INT,
배송지 VARCHAR(30),
주문일자 datetime,
PRIMARY KEY(주문번호),
foreign key(주문고객) references 고객(고객아이디),
foreign key(주문제품) references 제품(제품번호)
);

DESC 주문;

ALTER TABLE 고객 ADD 가입날짜 datetime;
ALTER TABLE 고객 ADD CONSTRAINT chk_avg check(나이>=20);

INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('apple','정소화',20,'gold','학생',1000);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('banana','jones',19,'gold','학생',1000);

select * from 고객;

alter table 고객 drop constraint chk_avg;

INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('banana','김선우',25,'vip','간호사',2500);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('carrot','고명석',28,'gold','교사',4500);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('orange','김용욱',22,'silver','학생',0);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('melon','성원용',35,'gold','회사원',5000);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('peach','오형준',null,'silver','의사',300);
INSERT INTO 고객 (고객아이디, 고객이름,나이, 등급,직업,적립금) VALUES ('peaR','채광주',31,'silver','회사원',500);


INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p01','그냥만두',5000,4500,'대한식품');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p02','매운쫄면',2500,5500,'민국푸드');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p03','쿵떡파이',3600,2600,'한빛제과');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p04','맛난초콜렛',1250,2500,'한빛제과');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p05','얼큰라면',2200,1200,'대한식품');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p06','통통우동',1000,1550,'민국푸드');
INSERT INTO 제품 (제품번호, 제품명,재고량, 단가,제조업체) VALUES ('p07','달콤비스켓',1650,1500,'한빛제과');

select * from 제품;

desc 주문;

INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o01','apple','p03',10,'서울시 마포구','2013-01-01');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o02','melon','p01',5,'인천시 계양구','2013-01-10');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o03','banana','p06',45,'경기도 부천시','2013-01-11');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o04','carrot','p02',8,'부산시 금정구','2013-02-01');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o05','melon','p06',36,'경기도 용인시','2013-02-20');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o06','banana','p01',19,'충청북도 보은군','2013-03-02');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o07','apple','p03',22,'서울시 영등포구','2013-03-15');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o08','pear','p02',50,'강원도 춘천시','2013-04-10');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o09','banana','p04',15,'전라남도 목포시','2013-04-11');
INSERT INTO 주문 (주문번호,주문고객,주문제품,수량,배송지,주문일자) VALUES ('o10','carrot','p03',20,'경기도 안양시','2013-05-22');

select * from 주문;

select AVG(단가) as 평균단가 from 제품;

select 제품명,제조업체 from 제품 where 제품번호 not in (select 주문제품 from 주문 where 주문고객 ='banana');

insert into 고객(고객아이디, 고객이름, 나이, 등급, 적립금) values ('tomato','정은심',36,'gold',4000);

select * from 고객;

select 고객이름,적립금 from 고객 where 적립금=(select max(적립금) from 고객);
select 제품명,제조업체 from 제품 where 제품번호 in(select 주문제품 from 주문 where 주문고객 = 'banana');
select 제품명,제조업체 from 제품 where 제품번호 not in(select 주문제품 from 주문 where 주문고객='banana');

create table 한빛제품 as (select 제품명, 재고량, 단가 from 제품 where 제조업체='한빛제과'); -- 한빛제과의 제품명 재고량 단가를 찾아서 한빛제품 테이블을 만들어 레코드를 삽입(임시로 테이블 만들때 좋음)
select * from 한빛제품;

select * from 제품;
update 제품 set 제품명='통큰파이' where 제품번호='p03';
set SQL_SAFE_UPDATES = 0; -- where절 없이 update 할 경우 safe mode로 막고 있어서 0으로 바꿔준다
update 제품 set 단가=단가*1.1;
update 주문 set 수량=5 where 주문고객 in(select 고객아이디 from 고객 where 고객이름='정소화');
select * from 주문;
delete from 주문 where 주문일자='2013-05-22';
delete from 주문 where 주문고객 in (select 고객아이디 from 고객 where 고객이름='정소화');

create view 우수고객(고객아이디, 고객이름, 나이) as select 고객아이디, 고객이름, 나이 from 고객 where 등급='vip' with check option;
select * from 우수고객;
create view 업체별제품수(제품업체,제품수) as select 제조업체,count(*) from 제품 group by 제조업체;
select * from 업체별제품수;
create view 제품1 as select 제품번호,재고량,제조업체 from 제품 with check option;
select * from 제품1;
insert into 제품1 values ('p08',1000,'대한식품'); -- view에 기본키 값이 있고 view가 생성될때 함수를 사용하여 정제된 view가 아니라서 수정이 가능하다
select * from 제품1;
create view 제품2 as select 제품명,재고량,제조업체 from 제품 with check option;
select * from 제품2;
insert into 제품2 values ('시원냉면',1000,'신선식품'); -- view에 기본키값이 있어야 수정이 가능하다!
drop view 우수고객 restrict;