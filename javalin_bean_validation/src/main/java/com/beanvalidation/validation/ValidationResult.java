package com.beanvalidation.validation;

public class ValidationResult {
    private String validationError;

    public ValidationResult(String validationError) {
        this.validationError = validationError;
    }

    public String getValidationError() {
        return validationError;
    }
}
