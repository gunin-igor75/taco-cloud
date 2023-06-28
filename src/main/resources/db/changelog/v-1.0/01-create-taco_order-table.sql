create table if not exists taco_order
(
    taco_order_id bigserial NOT NULL PRIMARY KEY,
    delivery_name varchar(50) NOT NULL,
    delivery_street varchar(50) NOT NULL,
    delivery_city varchar(50) NOT NULL,
    delivery_state varchar(20) NOT NULL,
    delivery_zip varchar(10) NOT NULL,
    cc_number varchar(16) NOT NULL,
    cc_expiration varchar(5) NOT NULL,
    cc_cvv varchar(3) NOT NULL,
    placed_at timestamp NOT NULL
    );

GO