package com.v1.resavation.validation.validators;

import com.v1.resavation.payload.request.RegistrationRequest;
import com.v1.resavation.validation.annotations.MatchedPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchedPasswordValidator implements ConstraintValidator<MatchedPassword, RegistrationRequest> {
    @Override
    public void initialize(MatchedPassword constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(RegistrationRequest registrationRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registrationRequest.getPassword().equals(registrationRequest.getVerifyPassword());
    }
}
