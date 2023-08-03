package ru.gil.tacocloud.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.model.Users;

import java.util.List;

@Repository
public interface TacoOrderRepository extends ListCrudRepository<TacoOrder, Long> {


    List<TacoOrder> findByUsersOrderByPlacedAtDesc(Users user, Pageable pageable);

}
