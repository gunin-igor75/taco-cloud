create table if not exists taco
(
    taco_id bigserial NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL,
    taco_order_id bigint NOT NULL,
    created_at timestamp NOT NULL,
    CONSTRAINT tacoOrderId FOREIGN KEY(taco_order_id) REFERENCES taco_order(taco_order_id)
);

GO
