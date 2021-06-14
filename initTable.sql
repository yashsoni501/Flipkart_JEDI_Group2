create database crs;
use crs;

create table `auth` (
    `uid` int primary key auto_increment, 
    `email` varchar(50) unique not null,
    `password` varchar(50) not null, 
    `userRole` varchar(50) not null
);

CREATE TABLE `student` (
    `admissionid` int primary key auto_increment, 
    `stuid` int unique not null,  
    `email` varchar(50) not null, 
    `name` varchar(50) not null,  
    `department` varchar(50) not null,  
    `session` varchar(50) not null,
    `approved` varchar(50) not null default 'FALSE',

    foreign key (stuid) references auth(uid)
);

CREATE TABLE `professor` (
    `empid` int primary key auto_increment, 
    `profid` int unique not null,
    `email` varchar(50) not null,
    `name` varchar(50) not null,
    `department` varchar(50) not null,
    
    foreign key (profid) references auth(uid)
);

CREATE TABLE `admin` (
    `empid` int primary key auto_increment, 
    `adminid` int unique not null,
    `email` varchar(50) not null,  
    `name` varchar(50) not null, 
    `designation` varchar(50),
    `status` varchar(50),

    foreign key (adminid) references auth(uid)
);


CREATE TABLE `course` (
    `courseid` int primary key auto_increment,  
    `courseName` varchar(50) unique not null, 
    `department` varchar(50) not null
);

CREATE TABLE `courseCatalog` (
    `courseid` int unique not null,
    `profid` int, 
    `semester` int not null, 
    `session` varchar(50) not null, 
    `credits` float not null,

    foreign key (courseid) references course(courseid)
);

CREATE TABLE `registeredCourse` (
    `regid` int primary key auto_increment, 
    `courseid` int not null, 
    `stuid` int not null, 
    `semester` int not null, 
    `session` varchar(50) not null, 
    `grade` varchar(50),

    foreign key (courseid) references course(courseid),
    foreign key (stuid) references auth(uid)
);

CREATE TABLE `semesterReportCard` (
    `srcid` int primary key auto_increment, 
    `stuid` int not null,
    `sgpa` varchar(50) not null, 
    `semester` int not null,

    foreign key (stuid) references auth(uid)
);

CREATE TABLE `payment` (
    `referenceId` varchar(50) primary key, 
    `stuid` int not null,
    `status` varchar(50) not null, 
    `amount` float not null, 
    `dateOfPayment` varchar(50) not null, 
    `semester` int not null, 
    `modeOfPayment` varchar(50) not null,

    foreign key (stuid) references auth(uid)
);


create table `constants` (
    `key` varchar(50) primary key, 
    `value` varchar(50) not null
);

insert into `constants` (`key`, `value`) values ('PAYMENT_WINDOW',  'FALSE');
insert into `constants` (`key`, `value`) values ('COURSE_WINDOW', 'FALSE');
insert into `constants` (`key`, `value`) values ('PROFESSOR_WINDOW', 'TRUE');


insert into `auth` (`email`, `password`, `userRole` ) values ('admin@crs', 'admin', 'ADMIN');

insert into `auth` (`email` , `password` , `userRole` ) values ('student@crs', 'Student1', 'STUDENT');
insert into `auth` (`email` , `password` , `userRole` ) values ('stud@crs', 'stud', 'STUDENT');

insert into `auth` (`email` , `password` , `userRole` ) values ('prof@crs', 'prof', 'PROFESSOR');
insert into `auth` (`email` , `password` , `userRole` ) values ('prof@hss', 'prof', 'PROFESSOR');
insert into `auth` (`email` , `password` , `userRole` ) values ('prof@cse', 'prof', 'PROFESSOR');


insert into `admin` (`adminid`, `email`, `name`, `designation`, `status`) values (1, 'admin@crs', 'Amit Balyan', 'HOD', 'ACTIVE');

insert into `student` (`stuid`, `email`, `name`, `department`, `session`, `approved`) values (2, 'student@crs', 'StudName', 'MATH', '2018', 'FALSE'); 
insert into `student` (`stuid`, `email`, `name`, `department`, `session`, `approved`) values (3, 'stud@crs', 'StudName', 'CSE', '2018', 'TRUE'); 

insert into `professor` (`profid`, `email`, `name`, `department`) values (4, 'prof@crs', 'ProfName', 'MATH');
insert into `professor` (`profid`, `email`, `name`, `department`) values (5, 'prof@hss', 'hssproff', 'HSS');
insert into `professor` (`profid`, `email`, `name`, `department`) values (6, 'prof@cse', 'cseproff', 'CSE');

insert into `course` (`courseName`, `department`) values ('Maths', "MATH");
insert into `course` (`courseName`, `department`) values ('Physics', "PHYSICS");
insert into `course` (`courseName`, `department`) values ('Economics', "HSS");
insert into `course` (`courseName`, `department`) values ('Phychology', "HSS");
insert into `course` (`courseName`, `department`) values ('Philosophy', "HSS");
insert into `course` (`courseName`, `department`) values ('Politics', "Social");
insert into `course` (`courseName`, `department`) values ('Chemistry', "Science");
insert into `course` (`courseName`, `department`) values ('Netorks', "CSE");
insert into `course` (`courseName`, `department`) values ('Databases', "CSE");

insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values ('3', null, '1' , '2018' , '3');
insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values ('4', 5, '1' , '2018' , '2');
insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values ('6', null, '1' , '2018' , '1');
insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values ('7', '6' , '1' , '2018' , '2');
insert into courseCatalog (`courseid`, `profid`, `semester`, `session`, `credits`) values ('8', null , '1' , '2018' , '3');















