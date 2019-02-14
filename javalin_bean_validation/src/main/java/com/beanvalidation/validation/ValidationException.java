package com.beanvalidation.validation;

public class ValidationException extends RuntimeException{
    private String errorMessage;

    public ValidationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
