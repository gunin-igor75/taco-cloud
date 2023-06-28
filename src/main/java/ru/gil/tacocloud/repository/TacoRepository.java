package ru.gil.tacocloud.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import ru.gil.tacocloud.model.Taco;

import java.util.List;

public interface TacoRepository extends ListCrudRepository<Taco, Long> {


    @Query("select * from taco where name = :name")
    List<Taco> findByName(@Param("name") String name);

    @Modifying
    @Query("update taco set name = :name where taco_id =:id")
    void updateTacoByName(@Param("id") Long id , @Param("name") String name);
}
