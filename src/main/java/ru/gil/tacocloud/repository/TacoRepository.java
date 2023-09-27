package ru.gil.tacocloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import ru.gil.tacocloud.model.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
