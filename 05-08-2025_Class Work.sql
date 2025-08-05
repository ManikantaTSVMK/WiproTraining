-- SQL  DDL  ( defining the structure of database and tables ), DML , DCL , TCL , DQL

create database wiprotraining;
use wiprotraining;
show tables;
-- DBMS  -- Data base management System -- MSAcess , XML , Excel -- File System -- No relationship -- Speed is slow for large data 
-- RDBMS -- Relational Database Management System -- stores data in tables  ,
-- tables are also linked with pk and fk, rules (Constraints) to ensure data accuracy ,
-- speed is faster because of indexing , MySQL , SQL Server , Oracle (PL/SQL) , PostgreSQL
-- Primary key  / Foreign key 

-- keyterms we use here tuples as rows -- ( cardinality ( total no of rows)
--     Attributes as column names  --- (Degree (total no of columns))
drop table todotask; -- It will the entire structure of a table with records
create table todotask
(
id int primary key ,
title varchar(255),
start_date Date,
due_date Date
);
-- table is a set of rows and columns
describe todotask;
select * from todotask;

insert into todotask values(101,'Learn Java' , '2025-08-05' , '2025-09-20');
insert into todotask values(102,'Coding Challenges' , '2025-08-05' , '2025-09-20');

-- Each task has a checklist
create table checklist
(
id int,
task_id int ,
title varchar(255) not null,
is_completed boolean not null default false,
primary key (id,task_id), -- composite key
foreign key(task_id) references todotask(id) on delete cascade
);
-- If you delete the task from todotask table then the data from child table i.e. checklist related data will also be deleted

describe checklist;
describe todotask;


truncate  table states; -- deleting all records at once but not a structure of a table 
truncate table cities;
drop table states;
drop table cities;
create table states
(statecode char(2) primary key,
name varchar(30));
insert into states values("IN", "Delhi");
describe states;
create table cities
(name varchar(30) , state_co char(2),
foreign key(state_co) references states(statecode) on delete cascade);
insert into cities values("Agra" , "IN");
describe cities;
select * from cities;
select * from states ; 

create table pin
(name varchar(34) ,state_co char(2),cities_id int ,
foreign key (id) references cities(id));
-- Error Code: 1451. Cannot delete or update a parent row: a foreign key constraint fails (`wiprotraining`.`cities`, CONSTRAINT `cities_ibfk_1` FOREIGN KEY (`state_co`) REFERENCES `states` (`statecode`))

set foreign_key_checks=1;

delete from states where statecode="IN"; -- delete a particular records matching with the conditions


select * from states;
update states set name="UP" where statecode= "IN";

select * from states where statecode="IN";
--  truncate , delete , drop , update , create , insert ,select ,foreign_key_checks , On delete cascade


