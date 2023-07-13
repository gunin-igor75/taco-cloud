package ru.gil.tacocloud.controller;

import jakarta.validation.Valid;
import lombok.Data;
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
import ru.gil.tacocloud.repository.TacoRepository;
import ru.gil.tacocloud.service.TacoUDRUtils;

import java.util.List;

import static ru.gil.tacocloud.model.Ingredient.Type.values;


@Slf4j
@Data
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Controller
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    private final TacoRepository tacoRepo;

    @ModelAttribute
    public void addIngredientToModel(Model model) {
        List<Ingredient> ingredients = ingredientRepo.findAll();
        Type[] types = values();
        for (Type type : types) {
            String nameType = type.toString().toLowerCase();
            List<Ingredient>  ingredientsByType = filterByType(ingredients, type);
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
        Taco newTaco = tacoRepo.save(taco);
        tacoOrder.addTaco(TacoUDRUtils.toTacoUDT(newTaco));
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                .filter(el -> el.getType().equals(type))
                .toList();
    }
}
