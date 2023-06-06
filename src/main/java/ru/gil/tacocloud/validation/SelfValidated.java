package ru.gil.tacocloud.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Set;

public final class SelfValidated {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    public final void validateSelf() {
        Set<ConstraintViolation<SelfValidated>> validate = VALIDATOR.validate(this);
        if (!validate.isEmpty()) {
            throw new ConstraintViolationException(validate);
        }
    }
}
