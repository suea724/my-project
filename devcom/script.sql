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
    lang varchar2(100) not null unique
);

create sequence seqLanguage;

select * from tblLanguage;

-- 스터디 게시판 - 사용 언어
create table tblStudyLanguage(
    seq number primary key,
    sseq references tblStudy(seq) on delete cascade not null,
    lseq references tblLanguage(seq) not null
);

create sequence seqStudyLanguage;

-- Q&A 게시판 질문글
create table tblQuestion (
    seq number primary key,
    id references tblUser(id) not null,
    title varchar2(100) not null,
    content varchar2(1000) not null,
    regdate date default sysdate not null,
    viewcnt number default 0 not null,
    status varchar2(10) default '미해결' check (status in ('해결', '미해결')) not null
);

create sequence seqQuestion;

-- Q&A 리스트 뷰
create or replace view vwQuestionList
as
select * from (select q.seq, q.title, q.regdate, q.viewcnt, q.status, q.content, u.name, (sysdate - q.regdate) as isnew, (select count(*) from tblAnswer a where a.qseq = q.seq) as answercnt from tblQuestion q inner join tblUser u on q.id = u.id order by seq desc);

-- Q&A 상세 뷰
create or replace view vwQuestion
as
select q.seq, q.title, q.content, q.regdate, q.viewcnt, q.status, u.name, u.id from tblQuestion q inner join tblUser u on q.id = u.id;


-- Q&A 게시판 답변글
create table tblAnswer(
    seq number primary key,
    id references tblUser(id) not null,
    qseq references tblQuestion(seq) not null,
    content varchar2(1000) not null,
    regdate date default sysdate not null
);

create sequence seqAnswer;

-- Q&A 게시판 답변 리스트 뷰
create or replace view vwAnswer
as
select a.seq, a.qseq, a.content, a.regdate, a.id, (select name from tblUser u where id = a.id) as name from tblAnswer a order by seq;






select * from vwQuestionList;

select * from vwAnswer;

select * from tblQuestion;
select * from tblAnswer;

insert into tblQuestion values (seqQuestion.nextval, 'sua', '제목 테스트', '내용 입니다.', default, default, default);

insert into tblAnswer values (1, 'test', 1, '그렇게 하는거 아닌데', sysdate);
insert into tblAnswer values (2, 'test', 1, '그렇게 하는거 아닌데', sysdate);
insert into tblAnswer values (3, 'test', 1, '그렇게 하는거 아닌데', sysdate);
insert into tblAnswer values (4, 'test', 1, '그렇게 하는거 아닌데', sysdate);
insert into tblAnswer values (5, 'test', 1, '그렇게 하는거 아닌데', sysdate);

update tblQuestion set status = '미해결';

commit;