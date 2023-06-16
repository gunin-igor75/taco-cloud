create table if not exists taco
(
    id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(50) not null,
    taco_order bigint not null,
    created_at timestamp not null,
    CONSTRAINT taco_order_id FOREIGN KEY(taco_order) REFERENCES taco_order(id)
);

GO
