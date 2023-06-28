package ru.gil.tacocloud.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gil.tacocloud.model.IngredientDouble;

@SpringBootTest
class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository repository;

    @Test
    public void testRep() {
        IngredientDouble jack = repository.getIngredientMapper("JACK").orElseThrow();
        System.out.println(jack);
    }
}