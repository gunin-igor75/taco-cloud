alter table taco_order add column user_id bigint references users(id);

GO;
