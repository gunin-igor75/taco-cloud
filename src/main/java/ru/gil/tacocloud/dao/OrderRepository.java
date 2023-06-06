package ru.gil.tacocloud.dao;

import ru.gil.tacocloud.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
