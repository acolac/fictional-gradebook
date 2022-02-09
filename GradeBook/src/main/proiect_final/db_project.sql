pragma foreign_keys = on;

create table STUDENT (
id integer primary key,
SNAME varchar (50) not null unique,
FNAME varchar (50) not null unique,
BIRTH_DATE datetime,
EMAIL text,
CNP integer not null unique
);

create table SCORE (
COURSE_ID integer not null references COURSE(id),
STUD_ID integer not null references STUDENT(id),
GRADE integer not null,
primary key (COURSE_ID, STUD_ID)
);

create table COURSE (
id integer primary key,
DESCRIPTION text not null unique
);

insert into STUDENT values(1, 'Ionut', 'Andrei', '2002-06-26', 'ionuta@gmail.com', 502062604);
insert into STUDENT values(2, 'Marian', 'Apostol', '2004-11-16', 'apomarian@gmail.com', 504111607);
insert into STUDENT values(3, 'Dragos', 'Condurache', '2003-02-04', 'condudra@gmail.com', 503020622);
insert into STUDENT values(4, 'George', 'Popa', '2000-12-01', 'popgeo@outlook.com', 500120119);
insert into STUDENT values(5, 'Sorin', 'Parvulescu', '1998-03-11', 'parvulesco@hotmail.com', 198031117);
insert into STUDENT values(6, 'Matei', 'Avrig', '1994-10-02', 'agrigmatei@gmail.com', 194100211);
insert into STUDENT values(7, 'Corneliu', 'Leu', '1999-09-12', 'leuco@gmail.com', 199091210);
insert into STUDENT values(8, 'Adelin', 'Damian', '1988-03-05', 'addedam@hotmail.com', 188030541);
insert into STUDENT values(9, 'Toma', 'Calin', '1997-11-12', 'calito@gmail.com', 19711120);
insert into STUDENT values(10, 'Denis', 'Dobos', '1991-12-12', 'dobomar@gmail.com', 197111201);

insert into SCORE values(1,1,60);
insert into SCORE values(2,4,70);
insert into SCORE values(3,5,100);
insert into SCORE values(4,2,90);
insert into SCORE values(5,3,80);
insert into SCORE values(6,3,70);
insert into SCORE values(7,5,30);
insert into SCORE values(8,6,50);
insert into SCORE values(2,7,50);
insert into SCORE values(3,8,70);
insert into SCORE values(1,9,65);
insert into SCORE values(4,10,40);
insert into SCORE values(6,10,20);
insert into SCORE values(8,7,100);
insert into SCORE values(8,9,90);
insert into SCORE values(1,2,80);
insert into SCORE values(1,3,90);
insert into SCORE values(3,4,100);
insert into SCORE values(4,7,90);
insert into SCORE values(6,8,70);
insert into SCORE values(5,10,80);
insert into SCORE values(8,1,100);

insert into COURSE values(1, 'Curs Project Management');
insert into COURSE values(2, 'Curs Programare Java');
insert into COURSE values(3, 'Curs Programare .NET');
insert into COURSE values(4, 'Curs Programare Python');
insert into COURSE values(5, 'Curs Testare Manuala');
insert into COURSE values(6, 'Curs Testare Automata');
insert into COURSE values(7, 'Curs REACT');
insert into COURSE values(8, 'Curs C/C++');

select * from student;
select * from score;
select * from course;

select stud_id, score from score sc
join student s on sc.stud_id = s.id
group by score;

select stud_id, name, course_id, description, score from score sc
join student s on sc.stud_id = s.id
join course c on sc.course_id = c.id
group by score asc;

select description, count() as NO_GRADES, MIN(score) as MIN_GRADE, MAX(score) as MAX_GRADE, AVG(score) as AVG_GRADE
from score sc
join course c on sc.course_id = c.id
group by id;
