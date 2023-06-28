package ru.gil.tacocloud.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.gil.tacocloud.model.IngredientDouble;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IngredientMapper implements RowMapper<IngredientDouble> {
    @Override
    public IngredientDouble mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  new IngredientDouble(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("type")
        );
    }
}
