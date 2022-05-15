
drop table Customer_TB cascade constraints;
drop table Room_TB cascade constraints;
drop table Type_TB cascade constraints;
drop table Reservation_TB cascade constraints;
drop table Accomodation_TB cascade constraints;
drop table Reply_TB cascade constraints;
drop table Review_TB cascade constraints;

drop sequence customer_seq;
drop sequence type_seq;
drop sequence reservation_seq;
drop sequence accomodation_seq;
drop sequence reply_seq;
drop sequence review_seq;

-- 관리자
create table Manager_TB(
    seq number primary key,
    id varchar2(16) not null,
    pw varchar2(16) not null
);

create sequence manager_seq;

-- 고객
create table Customer_TB(
    seq number primary key,
    name varchar2(50) not null,
    id varchar2(60) not null,
    pw varchar2(60) not null,
    tel varchar2(30) not null,
    email varchar2(40) null
);

create sequence customer_seq;


-- 객실 유형
create table TYPE_TB(
    seq number primary key,
    type varchar2(15) not null check (type in ('스탠다드룸', '슈페리어룸', '디럭스룸', '스위트룸', '패밀리룸')),
    capacity number not null check (capacity between 2 and 6),
    rate number
);

create sequence type_seq;

-- 객실
create table Room_TB(
    room_no number primary key,
    type_seq references Type_TB(seq) not null,
    cln_status varchar2(15) check (cln_status in ('청소필요', '청소중','청소완료'))
);


-- 예약
create table Reservation_TB(
    seq number primary key,
    cust_seq references Customer_TB(seq) not null,
    type_seq references Type_TB(seq) not null,
    rsv_date date default sysdate not null,
    guests number not null,
    check_in date not null,
    check_out date not null,
    status varchar2(12) not null check (status in ('예약완료', '예약취소', '투숙완료'))
);

create sequence reservation_seq;


-- 투숙
create table Accomodation_TB(
    seq number primary key,
    room_no references Room_TB(room_no) not null,
    rsv_seq references Reservation_TB(seq) not null
);

create sequence accomodation_seq;


-- 리뷰
create table Review_TB(
    seq number primary key,
    rsv_seq references Reservation_TB(seq) not null,
    rating number not null check (rating between 0 and 5),
    content varchar2(900) not null,
    regdate date default sysdate not null
);

create sequence review_seq;


-- 답글
create table Reply_TB(
    seq number primary key,
    rv_seq references Review_TB(seq) not null,
    content varchar2(300) not null,
    regdate date default sysdate not null 
);

create sequence reply_seq;
