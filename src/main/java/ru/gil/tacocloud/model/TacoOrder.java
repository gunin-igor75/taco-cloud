package ru.gil.tacocloud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.CreditCardNumber;
import ru.gil.tacocloud.validation.ValidationCity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "taco_order")
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate placedAt;

    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street is required")
    private String deliveryStreet;

    @NotBlank(message = "City is required")
    @ValidationCity(cities = {"Omsk", "Tara"})
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp = "(0[1-9]|1[0-2])(/)([2-9][0-9])",
            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0,
            message = "Invalid CVV")
    private String ccCVV;

    @OneToMany(mappedBy = "tacoOrder", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TacoOrder tacoOrder = (TacoOrder) o;
        return Objects.equals(id, tacoOrder.id);
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    public void addTaco(Taco ... tacos) {
        for (Taco taco : tacos) {
            taco.setTacoOrder(this);
            this.tacos.add(taco);
        }
    }

    public void removeTaco(Taco taco) {
        this.tacos.remove(taco);
        taco.setTacoOrder(null);
    }
}
