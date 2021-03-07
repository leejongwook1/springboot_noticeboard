drop table board;

create table board(
	no serial PRIMARY KEY,
	title varchar(30),
	comment varchar(50),
	name varchar(30),
	date date
);

insert into board values(default, '안녕하세요', '좋은 아침입니다', '이종욱', '2021-03-07');
insert into board values(default, 'hi~!!', 'have a nice day!', '김진', '2021-03-07');
insert into board values(default, '안녕하십니까', '하루 잘 보내세요', '이진', '2021-03-08');

select * from board;