package ru.gil.tacocloud.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.gil.tacocloud.model.Ingredient;

@Repository
public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {


}
