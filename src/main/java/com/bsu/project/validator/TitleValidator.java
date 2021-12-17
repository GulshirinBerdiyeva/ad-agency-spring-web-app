package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class TitleValidator extends AbstractValidator {
    private static final String TITLE_REGEX = "(?=.*[A-Za-zА-Яа-яЁё])(?=.*[A-Za-zА-Яа-яЁё])[\\wА-Яа-яЁё ']{3,40}";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(TITLE_REGEX, inputValue);
    }
}
