package com.bsu.project.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @author Gulshirin Berdiyeva
 */
@Component
public class FileNameValidator extends AbstractValidator {
    private static final String JPG = "jpg";
    private static final String JPG_FILE_NAME_REGEX = "[A-Za-zА-Яа-яЁё]+\\.(jpg)";

    @Override
    public boolean isValid(String inputValue) {
        if (!isNullOrEmpty(inputValue)) {
            if (inputValue.contains(JPG)) {
                return Pattern.matches(JPG_FILE_NAME_REGEX, inputValue);
            }
        }

        return false;
    }
}
