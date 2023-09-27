package ru.gil.tacocloud.service;

import org.springframework.data.domain.PageRequest;
import ru.gil.tacocloud.model.Taco;

import java.util.List;

public interface TacoService {
    Taco createTaco(Taco taco);

    List<Taco> findAll(PageRequest page);

    Taco findById(long id);
}
