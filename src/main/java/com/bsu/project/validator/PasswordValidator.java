package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class PasswordValidator  extends AbstractValidator {
    private static final String PASSWORD_REGEX = "(?=.*[A-ZА-ЯЁ])(?=.*[a-zа-яё])(?=.*\\d)[\\wА-Яа-яЁё]{8,20}";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(PASSWORD_REGEX, inputValue);
    }

}

