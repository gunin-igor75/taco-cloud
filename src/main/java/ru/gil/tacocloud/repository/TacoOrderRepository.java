package ru.gil.tacocloud.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.gil.tacocloud.model.TacoOrder;

import java.util.List;

public interface TacoOrderRepository extends ListCrudRepository<TacoOrder, Long> {

}
