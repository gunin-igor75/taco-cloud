package ru.gil.tacocloud.controller;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.gil.tacocloud.repository.IngredientRepository;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.Ingredient.Type;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoOrder;

import java.util.List;

import static ru.gil.tacocloud.model.Ingredient.Type.values;


@Slf4j
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Controller
@RequiredArgsConstructor
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = values();
        for (Type type : types) {
            String nameType = type.toString().toLowerCase();
            List<Ingredient> ingredientsByType = filterByType(ingredients, type);
            model.addAttribute(nameType, ingredientsByType);
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,
                              @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(el -> el.getType().equals(type))
                .toList();
    }
}
