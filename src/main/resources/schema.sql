drop table if exists post CASCADE;
drop table if exists users_table CASCADE;

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

