create table if not exists ingredient_ref
(
    ingredient varchar(4) not null,
    taco bigint not null,
    PRIMARY KEY (taco, ingredient),
    CONSTRAINT ingredient_id FOREIGN KEY(ingredient) REFERENCES ingredient(id),
    CONSTRAINT taco_id FOREIGN KEY(taco) REFERENCES taco(id)
);

GO