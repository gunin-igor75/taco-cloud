package ru.gil.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gil.tacocloud.model.TacoOrder;
import ru.gil.tacocloud.service.TacoOrderService;

import java.util.Objects;

@RestController
@RequestMapping(path = "/api/tacoOrders",
        produces ="application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
@RequiredArgsConstructor
public class TacoOrderControllerRest {

    private final TacoOrderService tacoOrderService;

    private final CacheManager cacheManager;

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> tacoById(@PathVariable("id") Long id) {
        TacoOrder tacoOrder = tacoOrderService.findByIdTacoOrder(id);
        return ResponseEntity.ok(tacoOrder);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<TacoOrder> creteTaco(@RequestBody TacoOrder tacoOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tacoOrderService.createTacoOrder(tacoOrder));
    }

    @GetMapping(value = "/clear-cache", params = "cache")
    public ResponseEntity<?> clearCache(@RequestParam (value = "cache") String cache) {
            Objects.requireNonNull(cacheManager.getCache(cache)).clear();
            return new ResponseEntity<>(HttpStatus.OK);
    }
}
