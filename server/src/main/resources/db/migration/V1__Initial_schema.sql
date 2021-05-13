create table project
(
    id   smallint unsigned primary key,
    name varchar(255)
);

create table task
(
    id         smallint unsigned primary key,
    project_id smallint unsigned,
    name       varchar(255)
);