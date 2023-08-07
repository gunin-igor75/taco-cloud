package ru.gil.tacocloud.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.repository.IngredientRepository;
import ru.gil.tacocloud.service.IngredientService;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientServiceImp implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public void createIngredient(Ingredient ingredient) {
        Optional<Ingredient> ingredientNew = ingredientRepository.findById(ingredient.getId());
        if (ingredientNew.isPresent()) {
            String message = String.format("Ingredient with id %s exists", ingredient.getId());
            log.debug(message);
            throw new ResolutionException(message);
        } else {
            ingredientRepository.save(ingredient);
        }
    }
}
