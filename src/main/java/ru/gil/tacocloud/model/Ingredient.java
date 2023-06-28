package ru.gil.tacocloud.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table("ingredient")
public class Ingredient  implements Persistable<String> {

    @Id
    private final String id;

    private final String name;

    private final Type type;

    @Transient
    private final boolean isNew;



    public Ingredient(String id, String name, Type type, boolean isNew) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public Ingredient(String id, String name, Type type) {
        this(id, name, type, false);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }

}
