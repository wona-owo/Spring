create table tbl_member(
    member_id varchar2(100) primary key,
    member_pw varchar2(30) not null,
    member_name varchar2(30) not null,
    member_phone char(13) not null,
    member_age number,
    member_gender char(1) check(member_gender in ('M','W')) not null,
    enroll_date date not null
); 

insert into tbl_member values ('admin', '1234', '관리자', '010-1234-1234', 10, 'M', sysdate);


create table tbl_notice (
    notice_no number primary key,
    notice_title varchar2(200) not null,
    notice_content varchar2(2000) not null,
    notice_writer varchar2(30) REFERENCES tbl_member(member_id) on delete cascade,
    notice_date date not null
);

create table tbl_notice_file (
    file_no number primary key,
    notice_no number REFERENCES tbl_notice(notice_no) on delete cascade,
    file_name varchar2(100) not null,
    file_path varchar2(100) not null 
);

create sequence seq_notice;


create table tbl_notice_title (
 notice_no number primary key,
 notice_title varchar2(200) not null,
 notice_content varchar2(2000) not null,
 notice_writer varchar2(30) REFERENCES tbl_member(member_id) on delete cascade,
 notice_date date not null
);

create sequence seq_notice_file;

insert into tbl_notice values (seq_notice.nextval, '제목1' , '내용1', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목2' , '내용2', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목3' , '내용3', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목4' , '내용4', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목5' , '내용5', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목6' , '내용6', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목7' , '내용7', 'admin', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목8' , '내용8', 'admin', sysdate);

insert into tbl_notice values (seq_notice.nextval, '제목9' , '내용9', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목10' , '내용10', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목11' , '내용11', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목12' , '내용12', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목13' , '내용13', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목14' , '내용14', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목15' , '내용15', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목16' , '내용16', 'user1', sysdate);

insert into tbl_notice values (seq_notice.nextval, '제목17' , '내용17', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목18' , '내용18', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목19' , '내용19', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목20' , '내용20', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목21' , '내용21', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목22' , '내용22', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목23' , '내용23', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목24' , '내용24', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목25' , '내용25', 'user1', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목26' , '내용26', 'user1', sysdate);

insert into tbl_notice values (seq_notice.nextval, '제목27' , '내용27', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목28' , '내용28', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목29' , '내용29', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목30' , '내용30', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목31' , '내용31', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목32' , '내용32', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목33' , '내용33', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목34' , '내용34', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목35' , '내용35', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목36' , '내용36', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목37' , '내용37', 'user2', sysdate);

insert into tbl_notice values (seq_notice.nextval, '제목38' , '내용38', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목39' , '내용39', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목40' , '내용40', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목41' , '내용41', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목42' , '내용42', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목43' , '내용43', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목44' , '내용44', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목45' , '내용45', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목46' , '내용46', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목47' , '내용47', 'user2', sysdate);

insert into tbl_notice values (seq_notice.nextval, '제목48' , '내용48', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목49' , '내용49', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목50' , '내용50', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목51' , '내용51', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목52' , '내용52', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목53' , '내용53', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목54' , '내용54', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목55' , '내용55', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목56' , '내용56', 'user2', sysdate);
insert into tbl_notice values (seq_notice.nextval, '제목57' , '내용57', 'user2', sysdate);




select rownum rnum, a.*
    from (
        select a.*
          from tbl_notice a
          order by notice_no desc
    ) a
    where rownum between 1 and 10;

select *
	from ( select rownum rnum, a.*
	     from ( select a.* 
                  from tbl_notice a
               order by notice_no desc ) a
	) a
	where rnum between 2 and 5;

select * from tbl_notice_file;



commit;