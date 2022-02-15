package com.bsu.project.validator;

/**
 * @author Gulshirin Berdiyeva
 */
public abstract class AbstractValidator {
    protected boolean isNullOrEmpty(String inputValue) {
        return inputValue == null || inputValue.trim().isEmpty();
    }

    public abstract boolean isValid(String inputValue);
}
