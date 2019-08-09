create database test;

use test;

create table member (
	id varchar(20) primary key,
    password varchar(20) not null,
    name varchar(20) not null,
    age int,
    tel varchar(20),
    address varchar(100)
);

select * from member;

delete from member where id in ('dghdr', 'null', 'restest', 'testaa', 'testjsp', 'z', '강종');

delete from member where tel ='33';

create table member (
	
);
