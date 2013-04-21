drop table if exists user;
create table user ( id integer auto_increment primary key, username varchar(20) not null unique , fullname varchar(100) not null, password varchar(20) not null );

drop table if exists post;
create table post ( id integer auto_increment primary key, title text not null , content text not null , date Datetime not null , user_id integer not null , foreign key ( user_id ) references user(id) );

drop table if exists comment;
create table comment ( id integer auto_increment primary key , content text not null , date Datetime not null , user_id integer not null , post_id integer not null , foreign key ( user_id ) references user(id) , foreign key (post_id) references post(id) );

drop table if exists test;
create table test ( id integer auto_increment primary key, one text collate utf8_general_ci not null , two text not null ) default charset=utf8 collate=utf8_general_ci;

alter table user convert to character set utf8 collate utf8_general_ci ;
alter table post convert to character set utf8 collate utf8_general_ci ;
alter table comment convert to character set utf8 collate utf8_general_ci ;
alter table test convert to character set utf8 collate utf8_general_ci;
