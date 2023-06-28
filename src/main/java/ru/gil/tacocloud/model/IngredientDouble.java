package ru.gil.tacocloud.model;

import lombok.Data;

@Data
public class IngredientDouble {

    private String id;

    private String name;

    private String type;

    public IngredientDouble(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

}
