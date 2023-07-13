package ru.gil.tacocloud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.repository.TacoOrderRepository;

import java.time.LocalDate;

@Repository
@RequiredArgsConstructor
public class TacoOrderServiceImp implements TacoOrderService{

    private final TacoOrderRepository orderRepository;


    @Override
    @Transactional
    public void createTacoOrder(TacoOrder order) {
        order.setPlacedAt(LocalDate.now());
        orderRepository.save(order);
    }
}
