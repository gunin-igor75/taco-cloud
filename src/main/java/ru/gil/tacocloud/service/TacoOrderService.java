package ru.gil.tacocloud.service;

import org.springframework.data.domain.Pageable;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.model.Users;

import java.util.List;

public interface TacoOrderService {
    void createTacoOrder(TacoOrder order);

    List<TacoOrder> getTacoOrdersForUser(Users user, Pageable pageable);
}
