create table users(
username varchar(50) not null primary key,
password varchar(100) not null,
enabled boolean not null
);

create table authorities 
(
username varchar(50) not null,
authority varchar(50) not null,
constraint fk_auth foreign key(username) references users(username)
);