package ru.gil.tacocloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
@AllArgsConstructor
public class IngredientRef {

    private String ingredientId;

}
