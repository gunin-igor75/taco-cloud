create table if not exists ingredient
(
    id varchar(4) NOT NULL PRIMARY KEY,
    name varchar(25) NOT NULL,
    type varchar(10) NOT NULL
);

GO