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
use wiprotraining;
create table contacts(id int auto_increment unique key , name varchar(40) not null, email varchar(50) not null) ;

-- Internally data is stored in the form of pages associated with the tables 
-- DDL  --create , drop, truncate alter command to add a new column , to modify the column datatype or size , change the column name(Rename) , add a constraint
-- DML --- update , delete ( remove rows individually withing the existing structure)  ,insert , 
-- DQL --- Select 
-- DCL --- Grant , Revoke
-- TCL -- Begin transactions , savepoint , roll back  , commit

-- alter command to add a new column
alter table contacts add officialaddress varchar(50);
desc contacts;
-- to modify the existing column datatype or size
alter table contacts modify officialaddress varchar(100);

-- to rename the exisitng column name(from old to new)
alter table contacts change officialaddress permanentaddress varchar(100);

-- adding a constraint 
alter table contacts modify email varchar(55) default 'xyz@gmail.com';

-- Constraints  --- Enforcing the data to check and verify(validation) while adding it in a table 
-- Types of constraints --  not null( column values can not be blank ) , 
-- 						    PK( PK will be one , cannot contain null values ,  clustured indexing ),FK,
--                          UK(multiple UK , It may have null values but only one , default it is non clustured indexing ) , 
--                          default , check 

select * from contacts;
desc contacts;
-- Now id is an auto increment and email is also having a default value so now to enter name and permanentaddress only in a table we call it as partial data to be entered

insert into contacts (name , permanentaddress) values("Niti" , "Delhi");

alter table contacts add constraint ch check(permanentaddress != officialaddress);

insert into contacts (name , permanentaddress , officialaddress) values("Arun" , "UP" , "Delhi");

update contacts set officialaddress="Kerala" where id =2; 

alter table contacts modify officialaddress varchar(50) unique;
update contacts set name="jiya" where id=1;
select * from contacts;

alter table contacts drop constraint ch;



create table data
(
id int primary key,
age int ,
contactno int unique not null,
address varchar(255),
pincode int , 
constraint pin unique (pincode),
constraint ag check(age>=18)

);


--  Data Types  --  int, boolean , decimal , datetime , timestamp , date , char ,time ,varchar,text , binary ,enum() , Blob 

create table t
(ts datetime
);

insert into t values(current_timestamp());
select * from t;
create table t1
(ts timestamp
);
insert into t1 values(current_timestamp());
select * from t1;
insert into t1 values(current_Date());

-- binary large object  -- resume ,  image 

create table image(
id int primary key auto_increment,
title varchar(255) not null,
image_data longblob not null
);

insert into image(title, image_data) values ("myphoto" , load_file('C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/image.png'));

select * from image;

select @@secure_file_priv;

-- temporary table  -- For testing purpose 
create temporary table tmp2 select * from orders where status = 'Shipped';
select * from tmp2 ;
select count(*) from tmp2;

show tables;

use classicmodels;

select * from orders;

-- distinct will give the unique records 
select distinct status from orders;

-- Aggregate --- scalar value (Single value)
select count(*) from orders where status='Shipped'; -- 303 records -- here it will total rows including null
-- Now from shipped status we want to check how many comments are provided in total
select count(comments) from orders where status='Shipped'; -- 62 comments -- here it will not include null values

select * from products;
select avg(buyprice) as 'Average Price' from products where productLine= 'Motorcycles';

select format(avg(buyprice) ,2) as 'Average Price' from products where productLine= 'Motorcycles' ;


select distinct productline from products;
-- group by -- It groups the products category wise  and then it shows the average price of each product category 
select productline , count(quantityInStock) as 'qty' from products group by productline having qty<9;

-- distinct , group by , having , aggregate functions(avg() , count() , format()) , alias name  , constraints(not null , default , check , uk ) , alter commands , autoincrement ,temp table , blob data type

select productline , max(buyprice) from products group by productline; 

select * from payments;

select max(amount) largestpayment from payments where month(paymentdate)=12;

-- Order by is used for displaying the data either in ascending which is by default or in descending
select customerNumber , amount from payments order by amount limit 5 ;
select datediff('2025-08-06' , '1975-10-12') as age;
select now();
use information_schema;

show tables;
select * from user_privileges;


show databases;


use classicmodels;

-- How many days are left in between requireddate and shippeddate with order number from orders table;

select ordernumber, datediff(requiredDate , shippedDate) as daysLeft from orders order by daysleftorders ;

select  * from orders where  year(shippedDate)=2003 ;

select extract(day from '2017-07-14') Day;
select *  , concat(firstName," " , lastName) as full_name from employees;
select concat(firstName," " , lastName) as full_name from employees;

/* Joins
Inner Join --  display the matching or common records from two tables
Left Join -- display all the data from left table and the matching record from right table
Right Join -- display all the data from right table and the matching record from left table
*/

desc products; -- Productline is a foreign key -- right table
desc productlines;  -- Productline is a primary key -- left table

/* syntax -- select  <columns of both the tables you want to display>
	         from <left table> Productlines
             inner join < Right table> products
             on <common columns>(such pk ( Left Table )and fk (Right table))
  */
  select  pl.productline , p.productVendor from productlines as pl 
  left join products as p
 using(productline);


select * from productlines;
select * from products;


-- return the ordernumber , order status and total sales from the orders and orderdetails

-- Error Code: 1055. Expression #1 of 
-- SELECT list is not in GROUP BY clause and contains nonaggregated column 'classicmodels.o.orderNumber' which is not functionally dependent on columns in GROUP BY clause; this is incompatible with sql_mode=only_full_group_by


select o.ordernumber , o.status  , sum(quantityordered*priceeach) as totalsales 
from orders o
inner join orderdetails od
using(ordernumber)
group by o.ordernumber having totalsales > 50000;

-- display all details of customers (Left join (Left table ) 
-- and their corresponding order (right table)

select count(distinct c.customernumber) from customers c left join orders o using(customernumber);

select o.* , c.* from customers c  left join orders o using(customernumber);
select count(*) from customers;
select count(*) from orders;

select * from customers;

create view vn as select distinct(c.customernumber) , o.ordernumber from customers c  left join orders o using(customernumber);
select * from vn where ordernumber is not null;

delete from vn where customernumber=124; -- It is hitting to the main table

/*
-- Subquery -- query inside another query ( Nested query)
both are independent and can execute independently 
-- Correlated SubQuery -- query inside another query
(but inner query depends upon the result of outer query
 so , inner query will not run independently)
*/

-- you have two different tables -- employee and department
-- who are the employees earning more than the department's  avg salary 

create  table departments
(department_id int primary key , department_name varchar(100));

create table employee
(employee_id int primary key,
employee_name varchar(100),
department_id int,
salary decimal(10,2),
foreign key (department_id) references departments(department_id));

insert into departments values (1,'sales'),
(2,'engineering'),
(3, 'hr') ;


insert into employee values(101, 'jiya' ,1,45454),
(102, 'preeti' ,1,35454),
(103, 'shubham' ,2,25454),
(104, 'Aman' ,2,45344),
(105, 'krishna ' ,3,15554),
(106, 'Anjali' ,3,65454);

-- employee earning more than their department average salary

-- self join with correlated subquery 
select employee_id ,employee_name from employee e where salary > 
 (select avg(salary) from employee e2 where e2.department_id = e.department_id);

-- correlated subquery run multiple times 
-- employees from sales department 

select employee_name , department_id from employee where department_id in (
select department_id from departments where department_name = 'Engineering');
;
select e.employee_name from employee e join departments d on e.department_id = d.department_id where d.department_name='Sales' ;

/* comparing values with an aggregate function -- subquery as compare to join
working with multiple tables and complex relationships you have created or when you have a large dataset  -- Join will be better instead of subquery will be slow
*/

-- employee  , product , order 

-- optimization exist and in  for large datasets when we are not sure then use exists 
-- Exists will stop searching once a match is found while in checks all records 
select employee_name from employee e where exists(
select 1 from departments d where d.department_id = e.department_id);

select * from cities where state in('up','delhi'); -- it is for category
select * from cities where pincode between 1002 and 1005 ;-- for specifying the range


create database student_database;
use student_database;
drop table student;
create table student (id int auto_increment primary key, name varchar(100), age int);

select * from student;
drop table student;



create table userlogin(username varchar(50), password varchar(20));
select * from userlogin;
insert into userlogin value ("mani","12345678");
create table student (id int auto_increment primary key, name varchar(100), age int);
select * from student;
drop table student;
-- Error Code: 1064. You have an error in your SQL syntax; 
-- check the manual that corresponds to your MySQL server version 

-- for the right syntax to use near '' at line 3

DELIMITER $$

CREATE PROCEDURE insert_user(IN sname varchar(20) , sage int)
BEGIN
	insert into student(name , age) values (sname , sage);
    select * from student;
END $$

DELIMITER ;

call  insert_user("Rushi",10);

select * from student;


-- 16-08-2025 --



/*Indexing -- for fast searching , for fast retrieving 
o(n) -  Binary Search or linear case

O(n) -- select * from customers where city="Delhi"
We have two types of indexing :
1) Clustured - The tables' data is stored along with the index itself when we apply a primary key in a database
   If you don't defing a PK -- then MySQL will  use the first unique key as clustured index in a table , if there is no unique key then it will create
   a hidden row-id as a clustured index..
   
   Fast one because it applied on a Primary key on a actual table itself

2) Non- clustured - the index is stored separately from the actual table data .
 It contains the column values  plus a pointer(row address / primary key)
 to the actual table row.
 
 Two look up requires (index+ table ) which is slower than the clustured one
 
 */
 
use wiprotraining;
create table customers(
customer_id int auto_increment primary key,
name varchar(100),
city varchar(100),
phone varchar(20)
);

insert into customers (name , city , phone) values
('Niti','Delhi','945495'),
('Nitin','Delhi','9675495'),
('Jatin','Agra','945566495'),
('Jiya','Bhimtal','944355495'),
('Kriti','Up','94235495');


Explain select * from customers where city='Delhi' and phoneno="";

create index idx_city on customers(city);

create index idx_name on customers(name);

create index idx_cityname on customers(city,name);

-- > JDBC Connection
create database wiprojdbc;
use wiprojdbc;

USE wiprotraining;

CREATE TABLE todo (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(255) NOT NULL,
description TEXT,
status VARCHAR(50),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


create database sampleproject;
use sampleproject;



























