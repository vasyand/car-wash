package ru.lieague.carwash.validation.validator;

import lombok.RequiredArgsConstructor;
import ru.lieague.carwash.service.UserService;
import ru.lieague.carwash.validation.annotation.EmailIsNotExistInDataBase;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UserEmailExistingValidator implements ConstraintValidator<EmailIsNotExistInDataBase, String> {
    private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.findByEmail(value).isPresent();
    }

}
