package ru.gil.tacocloud.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.gil.tacocloud.model.TacoOrder;

public interface TacoOrderRepository extends ListCrudRepository<TacoOrder, String> {
}
