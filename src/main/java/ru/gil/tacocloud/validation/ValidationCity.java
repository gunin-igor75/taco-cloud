package ru.gil.tacocloud.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityConstraintValidator.class)
public @interface ValidationCity {

    String[] cities();

    String message() default "Город не ддопустим ${validatedValue}, можно: {cities} ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
