drop database if exists library;
create database library;
use library;


create table book (
    book_id char(10) not null,
    title varchar(100),
    CONSTRAINT pk_book primary key (book_id)
);


create table book_authors (
    book_id char(10) not null,
    author_name varchar(60),
    type int not null default 1,
    CONSTRAINT fk_authors_book foreign key (book_id) references book(book_id),
    CONSTRAINT pk_authors primary key (book_id, author_name)
);


create table library_branch (
    branch_id int not null,
    branch_name varchar(30),
    address varchar(50),
    CONSTRAINT pk_branch_id primary key (branch_id)
);


create table book_copies (
    book_id char(10) not null,
    branch_id int not null,
    no_of_copies int,
    CONSTRAINT fk_copies_book foreign key (book_id) references book(book_id),
    CONSTRAINT fk_copies_branch foreign key (branch_id) references library_branch(branch_id)
);


create table borrower (
    card_no int not null,
    fname varchar(30),
    lname varchar(30),
    address varchar(50),
    phone varchar(20),
    CONSTRAINT pk_borrower primary key (card_no)
);


create table book_loans (
    loan_id int not null,
    book_id char(10) not null,
    branch_id int not null,
    card_no int not null,
    date_out date,
    due_date date,
    date_in date,
    CONSTRAINT pk_loan_id primary key(loan_id),
    CONSTRAINT fk_loans_book foreign key(book_id) references book(book_id),
    CONSTRAINT fk_loans_branch foreign key(branch_id) references library_branch(branch_id),
    CONSTRAINT fk_loans_borrower foreign key(card_no) references borrower(card_no)
);


create table next_id (
	idtype char(4) not null,
	nextid int not null
);
insert into next_id values ('card', 9042);
insert into next_id values ('loan', 22);

create table fines (
    loan_id int not null unique,
    card_no int not null,
    fine_amt decimal(5,2) not null,
    paid boolean default false,
    CONSTRAINT fk_fines_loan foreign key(loan_id) references book_loans(loan_id),
    CONSTRAINT fk_finess_borrower foreign key(card_no) references borrower(card_no)
);

create table users (
	username varchar(10) not null,
	password varchar(10) not null,
	CONSTRAINT pk_user primary key(username)
);
insert into users values ('admin', '12345');

create view SearchAvailableBooks as
select bc.book_id, b.title, GROUP_CONCAT(distinct ba.author_name) authors,bc.branch_id, bc.no_of_copies,
bc.no_of_copies - (select count(*) from book_loans bl
where b.book_id = bl.book_id AND bl.branch_id = bc.branch_id AND isnull(bl.date_in)) as available
from book b NATURAL JOIN book_copies bc NATURAL JOIN book_authors ba
group by b.book_id, bc.branch_id;

create view BorrowerFine as
(select bl.loan_id, bl.card_no, datediff(bl.date_in, bl.due_date) * 0.25 as amount, bl.date_in, f.paid from book_loans bl left join fines f on bl.loan_id = f.loan_id where not isnull(bl.date_in) having amount > 0)
union
(select bl.loan_id, bl.card_no, datediff(curdate(), bl.due_date) * 0.25 as amount, bl.date_in, f.paid from book_loans bl left join fines f on bl.loan_id = f.loan_id where isnull(bl.date_in) having amount > 0);

