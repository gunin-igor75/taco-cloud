package ru.gil.tacocloud.dao.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.gil.tacocloud.dao.IngredientRepository;
import ru.gil.tacocloud.model.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcIngredientRepository implements IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Ingredient> findAll() {
        return jdbcTemplate.query("select id, name, type from ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> ingredients = jdbcTemplate.query("select id, name, type from ingredient where id=?",
                this::mapRowToIngredient, id);
        return ingredients.isEmpty() ? Optional.empty() :
                Optional.of(ingredients.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update(
                "insert into ingredient(id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet row, int rowNum) throws SQLException {
        return new Ingredient(
                row.getString("id"),
                row.getString("name"),
                Ingredient.Type.valueOf(row.getString("type"))
        );
    }
}
