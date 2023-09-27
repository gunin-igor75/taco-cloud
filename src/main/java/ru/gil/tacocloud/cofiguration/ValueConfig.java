package ru.gil.tacocloud.cofiguration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.service.IngredientService;
import ru.gil.tacocloud.service.TacoService;

import java.util.Arrays;

import static ru.gil.tacocloud.model.Ingredient.Type;

@Configuration
@Profile("test")
public class ValueConfig {

    @Bean
    public CommandLineRunner dataLoader(
            IngredientService ingredientService,
            TacoService tacoService) {
        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FLTO", "Flour Tortilla", Type.WRAP);
            Ingredient cornTortilla = new Ingredient(
                    "COTO", "Corn Tortilla", Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "GRBF", "Ground Beef", Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Type.PROTEIN);
            Ingredient tomatoes = new Ingredient(
                    "TMTO", "Diced Tomatoes", Type.VEGGIES);
            Ingredient lettuce = new Ingredient(
                    "LETC", "Lettuce", Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterrey Jack", Type.CHEESE);
            Ingredient salsa = new Ingredient(
                    "SLSA", "Salsa", Type.SAUCE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Type.SAUCE);
            ingredientService.createIngredient(flourTortilla);
            ingredientService.createIngredient(cornTortilla);
            ingredientService.createIngredient(groundBeef);
            ingredientService.createIngredient(carnitas);
            ingredientService.createIngredient(tomatoes);
            ingredientService.createIngredient(lettuce);
            ingredientService.createIngredient(cheddar);
            ingredientService.createIngredient(jack);
            ingredientService.createIngredient(salsa);
            ingredientService.createIngredient(sourCream);
            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, salsa, cheddar));
            tacoService.createTaco(taco1);
            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    cornTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoService.createTaco(taco2);
            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, cornTortilla, tomatoes,
                    lettuce, salsa));
            tacoService.createTaco(taco3);
        };
    }
}
