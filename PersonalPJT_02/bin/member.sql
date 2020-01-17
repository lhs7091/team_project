drop table member;

create table member(
	id varchar(20) primary key,
	password varchar(100) not null,
	name varchar(20),
	phone char(13),
	account varchar(15),
	email varchar(30) not null,
	gamemoney int
)

--승률/누적상금을 저장하기 위한 공간
drop table resultgame;

create table resultgame(
	id varchar2(20) references member(id),				--회원id
	gamename varchar2(30),								--게임명
	gamenum int,											--게임참여횟수
	tot_win int,											--총 이긴 횟수
	tot_lose int,										--총 진 횟수
	tot_get_point int,									--누적 획득 포인트
	tot_loss_point int,									--누적 손실 포인트
	CONSTRAINT pri_result PRIMARY KEY (id, gamename)
);

--경마게임 승률
create table horseracing(
	id varchar(20) references member(id) primary key,		-- 회원id
	win int,												-- 승
	loose int,											-- 패
	get_point int,										-- 누적 획득포인트
	loss_point int										-- 누적 손실포인트
);


insert into asdf values('ddd',1,0,1,0);
select * from asdf where id=(select id from member where name='bbbb'); 

update member set gamemoney=gamemoney+1000 where id='aaa';