create database site;

use site;

-- 회원 테이블
create table member (
	member_id varchar(20) primary key,
    password varchar(20) not null,
    name varchar(30) not null,
    gender int not null,
    age int,
    birth date,
    tel varchar(20),
    address varchar(100),
    regist_date date not null,
    last_access_time timestamp
);

drop table member;

-- 게시글 테이블
create table article (
	article_id int auto_increment primary key,
    member_id varchar(20),
    title varchar(100) not null,
	content varchar(1000) not null,
    write_time timestamp not null,
    last_update_time timestamp not null,
    read_count int not null,
    
    foreign key (member_id) references member (member_id)
);

-- 댓글 테이블
create table comment (
	comment_id int auto_increment primary key,
    article_id int,
    member_id varchar(20),
    content varchar(1000) not null,
    write_time timestamp not null,
    
    foreign key (article_id) 
		references article (article_id) on delete cascade,
        
    foreign key (member_id) 
		references member (member_id)
);

select * from member;
select count(*) from article;
select * from comment where article_id=3;
use site;

select count(*) from comment where article_id=2
create view simpleArticle
as
select article_id, title, m.member_id, name, write_time, read_count
	from article a inner join member m 
    on a.member_id = m.member_id;

select * from simpleArticle;
use site;


create view detailArticle
as
select article_id, m.member_id, name, title, content, write_time, last_update_time, read_count
	from article a inner join member m 
    on a.member_id = m.member_id;
    
select * from detailArticle;

select * from simpleArticle where title like '%as%';

select article_id, count(*) from comment group by article_id;

create view commentCount as (
	select a.article_id, count(*) as 'comment_count' from comment c inner join detailArticle a on (c.article_id = a.article_id) group by a.article_id
);

drop view commentCount;

select * from commentCount;

select a.article_id, count(*) as 'comment_count' from comment c inner join detailArticle a on (c.article_id = a.article_id) group by a.article_id;

select d.article_id, d.title, d.name, d.write_time, d.read_count, c.comment_count
	from detailArticle d inner join commentCount c on (d.article_id = c.article_id);
	
	


-- 선생님이 만들어주신 쿼리
create view simpleArticle as
select a.article_id, a.title, m.member_id, m.name, a.write_time, a.read_count, count(c.comment_id) as 'comment_count'
from article a inner join member m on a.member_id = m.member_id
left outer join comment c on a.article_id = c.article_id
group by a.article_id;


drop view simpleArticle;

select * from simpleArticle;