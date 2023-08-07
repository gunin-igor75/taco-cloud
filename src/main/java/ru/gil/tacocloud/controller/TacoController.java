package ru.gil.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.service.TacoService;

@RestController
@RequestMapping(path = "/api/tacos",
        produces ="application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
@RequiredArgsConstructor
public class TacoController {

    private final TacoService tacoService;

    @GetMapping(params = "recent")
    public Iterable<Taco> recentTaco() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoService.findAll(page);
    }
}
