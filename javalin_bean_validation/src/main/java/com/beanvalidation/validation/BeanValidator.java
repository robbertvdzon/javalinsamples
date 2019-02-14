package com.beanvalidation.validation;

import io.javalin.Javalin;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

public class BeanValidator {
    private Javalin app;

    public BeanValidator(Javalin app) {
        this.app = app;
    }

    public void initValidation() {
        app.exception(ValidationException.class, (e, ctx) -> {
            ctx.status(400);
            ctx.json(new ValidationResult(e.getErrorMessage()));
        });
    }

    public void validateBean(Object myJson) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(myJson);
        if (!violations.isEmpty()) {
            String errorMsg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
            throw new ValidationException(errorMsg);
        }
    }

}
