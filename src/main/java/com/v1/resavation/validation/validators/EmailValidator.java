package com.v1.resavation.validation.validators;

import com.v1.resavation.exception.ApiBadRequestException;
import com.v1.resavation.validation.annotations.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        String regex = "^(.+)@(\\w+)\\.(\\w+)$";

        Pattern pattern = Pattern.compile(regex);
       //the if check might not be needed
        if (email == null) {
            throw new ApiBadRequestException("Error: email cannot ne null");
        }

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
