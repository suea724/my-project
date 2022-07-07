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

-- 모집 구분, 진행 방식, 모집 인원, 시작 예정, 예상 기간, 작성자, 등록일자, 제목, 내용, 연락처, 조회수
-- 스터디 게시판
create table tblStudy(
    seq number primary key,
    category varchar2(30),
    progressway varchar2(30) not null check (progressway in ('온라인', '오프라인')),
    recruitment number not null,
    startdate date not null,
    duration varchar2(30) not null,
    contact varchar2(100) not null,
    title varchar2(300) not null,
    content varchar2(4000) not null,
    regdate date default sysdate not null,
    viewcnt number default 0 not null,
    id references tblUser(id) not null
);

create sequence seqStudy;

-- 사용 언어
create table tblLanguage(
    seq number primary key,
    lang varchar2(100) not null
);

create sequence seqLanguage;

-- 스터디 게시판 - 사용 언어
create table tblStudyLanguage(
    seq number primary key,
    sseq references tblStudy(seq) not null,
    lseq references tblLanguage(seq) not null
);

select l.lang from tblStudy s inner join tblStudyLanguage sl on s.seq = sl.sseq inner join tblLanguage l on l.seq = sl.lseq;

create sequence seqStudyLanguage;

create or replace view vwStudy
as
select s.seq, s.title, s.category, u.name, s.viewcnt, s.startdate  from tblStudy s inner join tblUser u on s.id = u.id order by seq desc;

select * from vwStudy;

commit;