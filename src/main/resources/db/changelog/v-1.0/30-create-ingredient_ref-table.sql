create table if not exists ingredient_ref
(
    ingredient_id varchar(4) NOT NULL,
    taco_id bigint NOT NULL,
    PRIMARY KEY (ingredient_id, taco_id),
    CONSTRAINT ingredientId FOREIGN KEY (ingredient_id) REFERENCES ingredient(id),
    CONSTRAINT tacoId FOREIGN KEY(taco_id) REFERENCES taco(taco_id)
);

GO