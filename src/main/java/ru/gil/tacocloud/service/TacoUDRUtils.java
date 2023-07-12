package ru.gil.tacocloud.service;

import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.IngredientUDT;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoUDT;

public class TacoUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(
                ingredient.getName(),
                ingredient.getType()
        );
    }

    public static TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(
                taco.getName(),
                taco.getIngredients()
        );
    }

    public static Taco toTaco(TacoUDT taco) {
        return new Taco(taco.getName(),
                taco.getIngredients());
    }
}
