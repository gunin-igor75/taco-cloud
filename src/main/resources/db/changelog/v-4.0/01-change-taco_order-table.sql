alter table taco_order add column user_id bigint not null references users(id);

GO;
