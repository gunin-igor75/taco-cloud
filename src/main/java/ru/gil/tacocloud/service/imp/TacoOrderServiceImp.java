package ru.gil.tacocloud.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.model.Users;
import ru.gil.tacocloud.repository.TacoOrderRepository;
import ru.gil.tacocloud.service.TacoOrderService;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TacoOrderServiceImp implements TacoOrderService {

    private final TacoOrderRepository orderRepository;


    @Override
    @Transactional
    public void createTacoOrder(TacoOrder order) {
        List<Taco> tacos = order.getTacos();
        tacos.forEach(taco -> taco.setCreatedAt(LocalDate.now()));
        order.setPlacedAt(LocalDate.now());
        orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<TacoOrder> getTacoOrdersForUser(Users user, Pageable pageable) {
        return orderRepository.findByUsersOrderByPlacedAtDesc(user, pageable);
    }
}
