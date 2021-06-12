create database crs;
use crs;

create table `auth` (`uid` int primary key auto_increment, `email` varchar(32) not null, `password` varchar(32) not null, `userRole` varchar(32) not null);

CREATE TABLE `student` (`stuid` int primary key,  `email` varchar(32) not null,  `name` varchar(32) not null,  `department` varchar(32) not null,  `session` varchar(32) not null);
CREATE TABLE `professor` (  `profid` int primary key,  `email` varchar(32) not null,  `name` varchar(32) not null,  `department` varchar(32) not null);
CREATE TABLE `admin` (  `adminid` int primary key,  `email` varchar(32) not null,  `name` varchar(32) not null, `designation` varchar(32), `status` varchar(32));


CREATE TABLE `course` (  `courseid` int primary key auto_increment,  `courseName` varchar(32) not null, `department` varchar(32) not null);
CREATE TABLE `courseCatalog` (`courseid` int primary key,  `profid` int, `semester` int not null, `session` varchar(32) not null, `credits` float not null);
CREATE TABLE `registeredCourse` (`regid` int primary key auto_increment, `courseid` int not null,  `stuid` int not null, `semester` int not null, `session` varchar(32) not null, `grade` varchar(32) not null);

-- CREATE TABLE `reportCard` (  `stuId` varchar(32) ,  `cgpa` varchar(32) , `currentSem` int ); will be generated
CREATE TABLE `semesterReportCard` (`srcid` int primary key auto_increment, `stuid` int not null,  `sgpa` varchar(32) not null, `semester` int not null);

CREATE TABLE `payment` (`referenceId` varchar(32) primary key, `stuid` int not null,  `status` varchar(32) not null, `amount` float not null, `dateOfPayment` date not null, `semester` int not null, `modeOfPayment` varchar(32) not null);

create table `constants` (`key` varchar(32) primary key, `value` varchar(32));

insert into `constants` (`key`, `value`) values ('PAYMENT_WINDOW', 'FALSE');
insert into `constants` (`key`, `value`) values ('COURSE_WINDOW', 'FALSE');
insert into `constants` (`key`, `value`) values ('PROFESSOR_WINDOW', 'FALSE');

-- insert into `constants` (`key`, `value`) values ('REPORT_CARD', 'FALSE'); 


insert into `auth` (`email` , `password` , `userRole` ) values ('admin@crs', 'admin', 'ADMIN');
insert into `auth` (`email` , `password` , `userRole` ) values ('student@crs', 'Student1', 'PROFESSOR');
insert into `auth` (`email` , `password` , `userRole` ) values ('professor@crs', 'Professor1', 'STUDENT');



insert into `admin` (`adminid`, `email`, `name`, `designation`, `status`) values (1, 'admin@crs', 'Amit Balyan', 'HOD', 'ACTIVE');

insert into `student` (`stuid`, `email`, `name`, `department`, `session`) values (2, 'student@crs', 'StudName', 'MEMS', '2018-2022');
                
insert into `professor` (`profid`, `email`, `name`, `department`) values (3, 'professor@crs', 'ProfName', 'CE');





















