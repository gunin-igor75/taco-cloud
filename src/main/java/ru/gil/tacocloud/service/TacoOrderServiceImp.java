package ru.gil.tacocloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.model.*;
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
        List<TacoUDT> tacos = order.getTacos();
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos) {
            List<IngredientUDT> ingredients = taco.getIngredients();
            ingredients.forEach(taco::addIngredient);
        }
        order.setPlacedAt(LocalDate.now());
        orderRepository.save(order);
    }
}
