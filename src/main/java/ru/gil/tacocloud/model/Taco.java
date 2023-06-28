package ru.gil.tacocloud.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Table("taco")
public class Taco {

    @Id
    private Long tacoId;

    private LocalDate createdAt = LocalDate.now();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")
    @Transient
    private List<Ingredient> ingredients;

    @MappedCollection(idColumn = "taco_id")
    private Set<IngredientRef> ingredientRefs = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        ingredientRefs.add(new IngredientRef(ingredient.getId()));
    }
}
