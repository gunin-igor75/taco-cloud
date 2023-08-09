package ru.gil.tacocloud.service.imp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gil.tacocloud.exception_handler.ResourceException;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.model.Users;
import ru.gil.tacocloud.repository.TacoOrderRepository;
import ru.gil.tacocloud.service.TacoOrderService;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TacoOrderServiceImp implements TacoOrderService {

    private final TacoOrderRepository orderRepository;


    @Override
    @Transactional
    public TacoOrder createTacoOrder(TacoOrder order) {
        List<Taco> tacos = order.getTacos();
        tacos.forEach(taco -> taco.setCreatedAt(LocalDate.now()));
        order.setPlacedAt(LocalDate.now());
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public List<TacoOrder> getTacoOrdersForUser(Users user, Pageable pageable) {
        return orderRepository.findByUsersOrderByPlacedAtDesc(user, pageable);
    }

    @Override
    @Cacheable("tacoOrders") // Создаем запись в кеше
    public TacoOrder findByIdTacoOrder(long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> {
                    String message = String.format("TacoOrder with id %d not exists", id);
                    log.debug(message);
                    return new ResourceException(message);
                }
        );
    }

    @Override
    @CachePut(value = "tacoOrders", key = "#tacoOrder.id")  // Обновляем значение в кеше
    public TacoOrder editTacoOrder(TacoOrder tacoOrder) {
        TacoOrder tacoOrderBD = findByIdTacoOrder(tacoOrder.getId());
        return orderRepository.save(tacoOrderBD);
    }

    @Override
    @CacheEvict("tacoOrders")
    public void deleteTacoOrder(Long id) {
        TacoOrder tacoOrder = findByIdTacoOrder(id);
        orderRepository.delete(tacoOrder);
    }
}
