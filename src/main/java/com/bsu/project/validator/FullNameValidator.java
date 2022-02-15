package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class FullNameValidator  extends AbstractValidator {
    private static final String FULL_NAME_REGEX = "[A-ZА-ЯЁ][a-zа-яё]{1,30}( [A-ZА-ЯЁ][a-zа-яё]{1,30})?";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(FULL_NAME_REGEX, inputValue);
    }

}

