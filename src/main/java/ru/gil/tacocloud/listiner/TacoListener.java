package ru.gil.tacocloud.listiner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.relational.core.mapping.event.AbstractRelationalEventListener;
import org.springframework.data.relational.core.mapping.event.AfterSaveEvent;
import ru.gil.tacocloud.model.Taco;

@Slf4j
public class TacoListener extends AbstractRelationalEventListener<Taco> {
    @Override
    protected void onAfterSave(AfterSaveEvent<Taco> event) {
        log.info("After taco SaveEvent, taco:{}", event.getEntity());
    }
}
