create database crs;
use crs;

create table `auth` (`uid` int primary key auto_increment, `email` varchar(32) , `password` varchar(32), `userRole` varchar(32));

CREATE TABLE `student` (`stuid` int primary key,  `email` varchar(30) ,  `name` varchar(20) ,  `department` varchar(30) ,  `session` varchar(20) );
CREATE TABLE `professor` (  `profid` int primary key,  `email` varchar(32) ,  `name` varchar(32) ,  `department` varchar(32) );
CREATE TABLE `admin` (  `adminid` int primary key,  `email` varchar(32) ,  `name` varchar(32));


CREATE TABLE `course` (  `courseid` int primary key auto_increment,  `courseName` varchar(30) , `department` varchar(30) );
CREATE TABLE `courseCatalog` (`courseid` int primary key,  `profid` int, `semester` int, `session` varchar(32), `credits` float);
CREATE TABLE `registeredCourse` (`regid` int primary key auto_increment, `courseid` int,  `stuid` int, `semester` int, `session` varchar(32), `grade` varchar(2));

-- CREATE TABLE `reportCard` (  `stuId` varchar(20) ,  `cgpa` varchar(30) , `currentSem` int ); will be generated
CREATE TABLE `semesterReportCard` (`srcid` int primary key auto_increment, `stuid` int,  `sgpa` varchar(30), `semester` int);

CREATE TABLE `payment` (`referenceId` varchar(30) primary key, `stuid` int ,  `status` varchar(30) , `amount` float, `dateOfPayment` date , `semester` int, `modeOfPayment` varchar(20));

insert into `auth` (`email` , `password` , `userRole` ) values (`admin@crs`, `admin`, `ADMIN`);


insert into `auth` (`email` , `password` , `userRole` ) values ('admin@crs', 'admin', 'ADMIN');
insert into `auth` (`email` , `password` , `userRole` ) values ('student@crs', 'Student1', 'PROFESSOR');
insert into `auth` (`email` , `password` , `userRole` ) values ('professor@crs', 'Professor1', 'STUDENT');



insert into `admin` (  `adminid` int primary key,  `email` varchar(32) ,  `name` varchar(32))  values (1, `admin@crs`, `Amit Balyan`);

insert into `student` (`stuid` int primary key,  `email` varchar(30) ,  `name` varchar(20) ,  `department` varchar(30) ,  `session` varchar(20) ) 
				values (2, 'student@crs', 'StudName', 'MEMS', '2018-2022');
                
insert into `professor` (  `profid` int primary key,  `email` varchar(32) ,  `name` varchar(32) ,  `department` varchar(32) )
				values (3, 'professor@crs', 'ProfName', 'CE');





















