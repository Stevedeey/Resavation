package com.v1.resavation.validation.validation_domain;


import com.v1.resavation.exception.ApiBadRequestException;
import com.v1.resavation.validation.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidation implements ConstraintValidator<ValidPassword, String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        String regex = "^(([0-9]|[a-z]|[A-Z]|[@])*){8,20}$";

        Pattern pattern = Pattern.compile(regex);
       //This check might not be needed.
        if (password == null) {
            throw new ApiBadRequestException("Error: password cannot ne null");
        }

        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
