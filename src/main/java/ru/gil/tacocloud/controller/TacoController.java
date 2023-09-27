package ru.gil.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.service.TacoService;

import java.util.Collection;

@RestController
@RequestMapping(path = "/api/tacos",
        produces ="application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
@RequiredArgsConstructor
public class TacoController {

    private final TacoService tacoService;

    @GetMapping(params = "recent")
    public Collection<Taco> recentTaco() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoService.findAll(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Taco taco = tacoService.findById(id);
        return ResponseEntity.ok(taco);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Taco> creteTaco(@RequestBody Taco taco) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tacoService.createTaco(taco));
    }

}
