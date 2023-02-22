--liquibase formatted sql

--changeset shulika:1
create table users (
                        id int primary key GENERATED ALWAYS AS IDENTITY,
                        username varchar(100) not null unique,
                        first_name varchar(100) not null unique,
                        last_name varchar(100) not null,
                        email varchar(255) not null unique,
                        password varchar(255) not null,
                        status varchar(25) not null default 'ACTIVE',
                        created timestamp default current_timestamp,
                        updated timestamp default current_timestamp

);

--changeset shulika:2
create table roles (
                       id int primary key GENERATED ALWAYS AS IDENTITY,
                       name varchar(100) not null unique,
                       status varchar(25) not null default 'ACTIVE',
                       created timestamp default current_timestamp,
                       updated timestamp default current_timestamp
);

--changeset shulika:3
create table user_roles (
                        user_id int references users(id),
                        role_id int references roles(id)
);

--changeset shulika:4
insert into roles(name) VALUES ('ROLE_ADMIN');

--changeset shulika:5
insert into roles(name) VALUES ('ROLE_USER');

