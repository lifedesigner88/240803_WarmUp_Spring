package org.example.hello2.config.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) return false;

        return value.matches("^010-\\d{4}-\\d{4}$");
    }
}
