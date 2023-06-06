package ru.gil.tacocloud.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CityConstraintValidator implements ConstraintValidator<ValidationCity, String> {

    private Set<String> cities;

    @Override
    public void initialize(ValidationCity constraintAnnotation) {
        cities = Stream.of(constraintAnnotation.cities())
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return  cities.contains(value);
    }
}
