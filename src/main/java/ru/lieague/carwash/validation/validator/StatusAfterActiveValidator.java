package ru.lieague.carwash.validation.validator;

import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.validation.annotation.StatusAfterActive;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static ru.lieague.carwash.model.BookingStatus.CANCELED;
import static ru.lieague.carwash.model.BookingStatus.PAID;


public class StatusAfterActiveValidator implements ConstraintValidator<StatusAfterActive, BookingStatus> {

    @Override
    public boolean isValid(BookingStatus value, ConstraintValidatorContext context) {
        return value == PAID
                || value == CANCELED;
    }

}
