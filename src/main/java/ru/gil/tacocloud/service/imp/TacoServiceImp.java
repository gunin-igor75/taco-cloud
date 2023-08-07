package ru.gil.tacocloud.service.imp;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.gil.tacocloud.model.Taco;
import ru.gil.tacocloud.repository.TacoRepository;
import ru.gil.tacocloud.service.TacoService;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TacoServiceImp implements TacoService {

    private final TacoRepository tacoRepository;


    @Override
    public void createTaco(Taco taco) {
        Optional<Taco> tacoNew = tacoRepository.findById(taco.getId());
        if (tacoNew.isPresent()) {
            String message = String.format("Taco with id %d exists", taco.getId());
            log.debug(message);
            throw new ResolutionException(message);
        } else {
            tacoRepository.save(taco);
        }
    }

    @Override
    public List<Taco> findAll(PageRequest page) {
        Page<Taco> tacos = tacoRepository.findAll(page);
        return tacos.getContent();
    }
}
