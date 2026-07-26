package dev.orion.commons.utils;

import dev.orion.commons.exception.BusinessException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    private PhoneValidator(){}

    private static final String regex = "^(\\+959|959|09)\\d{7,9}$";
    private static final Pattern pattern = Pattern.compile(regex);

    public static void validate(String phone){
        Matcher matcher = pattern.matcher(phone);
        if(!matcher.matches()) throw new BusinessException("invalid phone number.");
    }
}
