package ru.gil.tacocloud.service;

import org.springframework.data.domain.PageRequest;
import ru.gil.tacocloud.model.Taco;

import java.util.List;

public interface TacoService {
    void createTaco(Taco taco);

    List<Taco> findAll(PageRequest page);
}
