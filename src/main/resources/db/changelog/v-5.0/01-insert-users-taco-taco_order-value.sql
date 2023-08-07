
delete from ingredient_ref;
delete from taco;
delete from taco_order;
delete from users;

insert into users (username, password, full_name, street, city,region, zip, phone_number,role)
values ('igor', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'igor gunin', '10 лет Октября',
'omsk', 'Омская обоасть', '644070','89139792222', 'USER' );

insert into taco_order (delivery_name, delivery_street, delivery_city,  delivery_state, delivery_zip,
    cc_number,cc_expiration, cccvv, placed_at, user_id)
values ('taco-order-first', '10 лет Октября', 'Omsk',  'Омская обоасть', '644070', '4627100101654724','12/24', '555',  '2023-08-06', 1),;


insert into taco(name, taco_order, created_at)
values ('first_taco', 1, '2023-08-06'),
('second_taco', 1, '2023-08-07');

insert into ingredient_ref(ingredient, taco)
values('FLTO',1), ('GRBF', 1), ('TMTO', 1),
('FLTO',1), ('CARN', 1), ('TMTO', 1);