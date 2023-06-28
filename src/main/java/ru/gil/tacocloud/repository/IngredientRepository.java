package ru.gil.tacocloud.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gil.tacocloud.mapper.IngredientMapper;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.IngredientDouble;

import java.util.Optional;

public interface IngredientRepository extends ListCrudRepository<Ingredient, String> {

    @Query(value = "select * from ingredient where id = :id", rowMapperClass = IngredientMapper.class )
    Optional<IngredientDouble> getIngredientMapper(@Param("id") String id);

}
