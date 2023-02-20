drop table if exists post CASCADE;
drop table if exists users_table CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

create table post (
    id integer not null,
    description varchar(255),
    user_id integer,
    primary key (id)
                  );

create table users_table (
    id integer not null,
    name varchar(255),
     primary key (id)
                         );

