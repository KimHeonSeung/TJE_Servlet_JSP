create database kkh;
use kkh;

-- 멤버
-- ID(PK), PW not null, 닉, 연락처, 이메일, 가입날짜 not null, 접속일수(현재 날짜-가입날짜)
create table user (
	user_id varchar(10) primary key,
    user_pw varchar(15) not null,
    user_nick varchar(8) not null unique,
    user_tel int(11),
    user_mail varchar(30),
    user_regist_date date not null,
    user_count_day int(10)
);

-- 게시판ID&게시판명
-- 게시판_ID(PK) auto in, 게시판명(not null)
create table board_info (
	board_id int(3) auto_increment primary key,
    board_name varchar(8) 
);

-- 전체 댓글 테이블
create table total_comment (
	comment_id int(6) primary key auto_increment,
    article_num int(5),
    user_id varchar(10),
    content varchar(100) not null,
    write_time Timestamp not null,
    
     foreign key (article_num) references total_board (article_num),
     foreign key (user_id) references user (user_id)
);

-- 대댓글을 위한 속성 추가
alter table total_comment add(
	comment_parent int(8),
    comment_depth int(2),
    comment_order int(8)
);

-- id와 닉네임 모두 표기 가능한 테이블 구성
create view comments as
	select t.comment_id, t.article_num, t.user_id, u.user_nick, t.content, t.write_time
		from user u, total_comment t
			where u.user_id = t.user_id;



select * from article_user, article_guest where article_user.board_id = 1;

select * from article_user left outer join article_guest on article_user.board_id=1;


select u.article_user_id, board_id, u.title_user, u.user_nick, u.content_user, u.write_date, u.update_date, u.like_count, u.read_count, g.article_guest_id, g.title_guest, g.guest_nick, g.content_guest, g.write_date, g.update_date, g.like_count, g.read_count, g.article_password 
from
(select u.article_user_id, g.article_guest_id, u.board_id, g.board_id, u.title_user, g.title_guest, u.user_nick, g.guest_nick, u.content_user, g.content_guest, u.write_date, g.write_date, u.update_date, g.update_date, u.like_count, g.like_count, u.read_count, g.read_count, g.article_password 
	from article_user u left outer join article_guest g on u.board_id=g.board_id
union
select u.article_user_id, g.article_guest_id, u.board_id, g.board_id, u.title_user, g.title_guest, u.user_nick, g.guest_nick, u.content_user, g.content_guest, u.write_date, g.write_date, u.update_date, g.update_date, u.like_count, g.like_count, u.read_count, g.read_count, g.article_password 
	from article_user u right outer join article_guest g on u.board_id=g.board_id) a;


select * from test1 a left outer join test2 b on a.test_a_1=b.test_a_2;





-- 중요 !! 새로 만든 테이블. 기존의 테이블은 일단 지우지 않고 냅뒀다.
-- 글 번호, board id, writer id, writer nick, 글제목, 글내용, 작성일, 조회수, 좋아요, 삭제비번
create table total_board (
	article_num int(5) primary key auto_increment,
    board_id int(3),
    writer_id varchar(10) not null,
    writer_nick varchar(8) not null,
    article_title varchar(30) not null,
    article_content varchar(500) not null,
    write_date timestamp not null,
    read_count int(6) default 0,
    like_count int(4) default 0,
    del_pw int(3),
    
    foreign key (board_id) references board_info (board_id)
);

select * from total_board order by article_num asc;
update total_board set read_count=1 where article_num=3;

insert into total_board values (null, 1, 'BB', 'EE', 'title5', 'content5', now(), 0, 0, null);

-- 글 목록을 위한 뷰
create view simpleBoard as
	select board_id, article_num, writer_nick, article_title, write_date, read_count, like_count from total_board order by write_date desc;
    
-- 글을 들어갔을 때 위한 뷰
create view detailBoard as
	select article_num, article_title, writer_nick, article_content, write_date, read_count, like_count from total_board;


-- id까지 포함된 뷰
create view simpleBoardWithUser as
	select board_id, article_num, writer_id, writer_nick, article_title, write_date, read_count, like_count from total_board order by write_date desc;
-- id까지 포함된 뷰
create view simpleBoardNameWithUser as
	select b.board_id, b.board_name, article_num, writer_id, writer_nick, article_title, write_date, read_count, like_count from total_board t, board_info b where t.board_id = b.board_id order by write_date desc;


select * from simpleBoard;
select * from detailBoard;
select * from simpleBoardWithUser;
select * from simpleBoardNameWithUser;


select * from detailBoard where article_num = 2;

-- A 1번글 좋아요 (A,1)
create table like_info (
	user_id varchar(10),
    article_num int(5),
	
    primary key (user_id, article_num),
    foreign key (user_id) references user (user_id),
    foreign key (article_num) references total_board (article_num)
);
insert into like_info values ('1',1);
select * from like_info;

select * from simpleBoard;

select @RNUM:=@RNUM+1, a.article_num 
	from (select * from simpleBoard order by article_num desc) a, (select @RNUM := 0) b
		where @RNUM:=@RNUM+1 < 5;
    
select rnum, article_num
		from (select @RNUM := @RNUM + 1 as rnum, a.article_num 
				from (select * from simpleBoard order by article_num desc) a, (select @RNUM := 0) b) as X
					where rnum <= 10 and rnum >= 6;

select s.* from (select * from simpleBoard order by article_num desc) s where board_id = 1 limit 0, 5;


-- thisBoardList
 select s.board_id, board_name, article_num, writer_nick, article_title, write_date, read_count, like_count
	from board_info b, simpleBoard s
		where s.board_id = b.board_id and s.board_id=1 limit 0,5;   
    
select @RNUM := 0;  

-- 로그인된 글
-- 글번호(PK) auto in, 게시판_ID(FK-게시판ID&게시판명), 제목, 닉(FK-멤버), 내용, 날짜, 수정날짜, 추천수, 조회수
-- create table article_user (
-- 	article_user_id int(8) auto_increment primary key,
--    board_id int(3),
--    title_user varchar(10) not null,
--    user_nick varchar(8),
--    content_user varchar(500) not null,
--    write_date date not null,
--    update_date timestamp,
--    like_count int(4),
--    read_count int(6) default 0,
    
--    foreign key (board_id) references board_info (board_id),
--   foreign key (user_nick) references user (user_nick)
-- );

-- insert into article_user values (null, 1, '2님의 글', '2', '2번님이 작성한 게시글', now(), now(), 0, 0);

-- 로그인 안된 글
-- 글번호(PK) auto in, 게시판_ID(FK-게시판ID&게시판명), 제목, 유동닉, 내용, 날짜, 수정날짜, 추천수, 조회수, 글비번 notnull
-- create table article_guest (
-- 	article_guest_id int(8) auto_increment primary key,
--    board_id int(3),
--    title_guest varchar(10) not null,
--    guest_nick varchar(8) not null,
--    content_guest varchar(500) not null,
--    write_date date not null,
--    update_date timestamp,
--    like_count int(4),
--    read_count int(6) default 0,
--    article_password int(3) not null,
--    
--    foreign key (board_id) references board_info (board_id)
-- );
-- select * from article_guest;
-- insert into article_guest values (null, 2, '손님2의 글', '손님2', '손님2이 작성한 게시글', now(), now(), 0, 0, 123);

-- create table comment_user (
-- 	comment_user_id int(8) primary key,
--    article_user_id int(8),
--    comment_user varchar(100) not null,
--    user_nick varchar(8),
--    write_date timestamp not null,
--    like_count int(4),
    
--    foreign key (user_nick) references user (user_nick),
--    foreign key (article_user_id) references article_user (article_user_id)
-- );


-- 로그인 안된 댓글
-- 댓글_ID(PK), 글번호(FK-글), 내용, 유동닉, 날짜, 추천수, 댓글비번
-- create table comment_guest (
-- 	comment_guest_id int(8) primary key,
--    article_guest_id int(8),
--    comment_guest varchar(100) not null,
--   guest_nick varchar(8) not null,
--    write_date timestamp not null,
--    like_count int(4),
--    comment_password int(3) not null,
    
--   foreign key (article_guest_id) references article_guest (article_guest_id)
-- );
-- drop table comment_guest;
-- '글'에서 뷰1
-- 글번호(PK), 게시판_ID(FK), 제목, 글쓴이, 날짜, 추천수, 조회수.

-- create view simpleArticle
-- as
-- select article_id, title, m.member_id, name, write_time, read_count
-- 	from article a inner join member m 
--     on a.member_id = m.member_id;
  