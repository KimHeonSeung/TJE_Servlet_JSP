create database carrot;
use carrot;

create table user (
	user_id varchar(20) primary key,
    password varchar(20) not null,
    nickname varchar(20) not null,
    tel varchar(20) not null,
    location_city varchar(10) not null,
    location_gu varchar(10) not null,
    regist_date timestamp not null
);

create table article(
	article_id int auto_increment primary key,
    user_id varchar(20) not null,
    category varchar(20) not null,
	title varchar(50) not null,
    content varchar(1000) not null,
    price int not null,
    location_city varchar(10) not null,
    location_gu varchar(10) not null,
    write_time timestamp,
    read_count int,
    like_count int,
    foreign key (user_id) references user(user_id) on delete cascade
);
create table interest(
	user_id varchar(20) not null,
    article_id int not null,
    foreign key (user_id) references user(user_id) on delete cascade,
    foreign key (article_id) references article(article_id) on delete cascade
);
select * from interest;
delete from interest;

create table comment(
	comment_id int auto_increment primary key,
    article_id int,
    user_id varchar(20),
    nickname varchar(20),
    content varchar(1000) not null,
	write_time timestamp not null,
    foreign key (article_id) 
		references article(article_id) 
        on delete cascade,
    foreign key (user_id) 
		references user(user_id) 
);

create table itemImage(
	image_id int auto_increment primary key,
    detailUserImgName varchar(200) not null,
    detailSysImgName varchar(200) not null,
    article_id int,
    foreign key (article_id) references article(article_id)
    on delete cascade
    );
create table thumbNail(
	thumbNail_id int auto_increment primary key,
    userImgName varchar(200) not null,
    sysImgName varchar(200) not null,
    article_id int,
    foreign key (article_id) references article(article_id)
    on delete cascade
    );

create view simpleArticle
as
select a.article_id, u.user_id,u.nickname,t.userImgName,t.sysImgName
 , a.category, a.title, a.location_city, a.location_gu,
a.price, a.read_count,  count(i.article_id) as like_count, count(c.comment_id) as comment_count
from article a left outer join comment c 
  on a.article_id = c.article_id
  join user u
  on a.user_id = u.user_id
  join thumbNail t
  on a.article_id = t.article_id
   join interest i
  on a.article_id = i.article_id
group by a.article_id;

create view detailArticle
as
select a.article_id, u.user_id ,u.nickname,
 a.category, a.title ,a.content , a.location_city as 'city', a.location_gu as 'gu',
a.write_time,a.price, a.read_count, count(i.article_id) as like_count, count(c.comment_id) as comment_count
from article a left outer join comment c 
  on a.article_id = c.article_id
  join user u
  on a.user_id = u.user_id
  join interest i
  on a.article_id = i.article_id
 group by a.article_id;

select * from user;
select * from article;
select * from comment;
select * from thumbNail;
select * from itemImage;
select * from simpleArticle;
select * from detailArticle;
select * from interst;

drop table user;
drop table article;
drop table comment;
drop table itemImage;
drop table thumbNail;
drop view simpleArticle;
drop view detailArticle;


delete from user;
delete from thumbNail;
delete from itemImage;
delete from article;

select * 
from simpleArticle
order by article_id desc;
