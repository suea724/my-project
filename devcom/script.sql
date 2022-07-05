-- 회원
create table tblUser(
    id varchar2(30) primary key,
    pw varchar2(30) not null,
    name varchar2(30) not null,
    grade number(1) default 1 not null,
    regdate date default sysdate not null,
    pic varchar2(100) default 'pic.png' not null
);

select * from tblUser;

-- 커뮤니티 글
create table tblCommunity(
    seq number primary key,
    title varchar(300) not null,
    content varchar(4000) not null,
    regdate date default sysdate not null,
    viewcnt number default 0 not null,
    id varchar2(30) not null references tblUser(id)
);

create sequence seqCommunity;

-- 커뮤니티 리스트
create or replace view vwCommunity
as
select c.*, u.name from tblUser u inner join tblCommunity c on u.id = c.id order by seq desc;

select * from vwCommunity;

commit;