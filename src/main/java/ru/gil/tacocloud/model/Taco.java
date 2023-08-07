package ru.gil.tacocloud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "taco")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient")

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "ingredient_ref",
    joinColumns = @JoinColumn(name = "taco"),
    inverseJoinColumns = @JoinColumn(name = "ingredient"))
    private List<Ingredient> ingredients;

    @ManyToOne(optional = false)
    @JoinColumn(name = "taco_order")
    private TacoOrder tacoOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Taco taco = (Taco) o;
        return Objects.equals(id, taco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addIngredient(Ingredient... ingredients) {
        for (Ingredient ingredient : ingredients) {
            this.ingredients.add(ingredient);
            ingredient.getTacos().add(this);
        }
    }

    public void removeIngredient(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.getTacos().remove(this);
    }

}
