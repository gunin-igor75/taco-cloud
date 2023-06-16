package ru.gil.tacocloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.model.Ingredient;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.repository.TacoOrderRepository;
import ru.gil.tacocloud.repository.TacoRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TacoOrderServiceImp implements TacoOrderService{

    private final TacoOrderRepository orderRepository;

    private final TacoRepository tacoRepository;

    @Override
    @Transactional
    public void createTacoOrder(TacoOrder order) {
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            List<Ingredient> ingredients = taco.getIngredients();
            taco.setCreatedAt(LocalDate.now());
            ingredients.forEach(taco::addIngredient);
        }
        order.setPlacedAt(LocalDate.now());
        orderRepository.save(order);
    }
}
