package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class BalanceValidator extends AbstractValidator {
    private static final String DOUBLE_NUMBER_REGEX = "^[1-9][0-9][0-9]$|1000";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(DOUBLE_NUMBER_REGEX, inputValue);
    }
}
