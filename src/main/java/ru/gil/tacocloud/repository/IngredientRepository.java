package ru.gil.tacocloud.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.gil.tacocloud.model.Ingredient;

public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {


}
