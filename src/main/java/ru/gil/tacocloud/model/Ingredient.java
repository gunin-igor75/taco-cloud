package ru.gil.tacocloud.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "ingredient")
@JsonIgnoreProperties(value = {"tacos"})
public class Ingredient{

    @Id
    private  String id;

    private  String name;

    @Enumerated(EnumType.STRING)
    private  Type type;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Taco> tacos;
    public Ingredient() {
    }

    public Ingredient(String id, String name, Type type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
    }

}
