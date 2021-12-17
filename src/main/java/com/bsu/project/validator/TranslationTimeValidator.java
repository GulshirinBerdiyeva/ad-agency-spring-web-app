package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class TranslationTimeValidator extends AbstractValidator {
    private static final String TRANSLATION_TIME_REGEX = "(0?[1-9]|1[0-9]|2[0-4])";

    @Override
    public boolean isValid(String inputValue) {
        return !isNullOrEmpty(inputValue) && Pattern.matches(TRANSLATION_TIME_REGEX, inputValue);
    }
}
