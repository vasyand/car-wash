package ru.lieague.carwash.validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lieague.carwash.model.BookingStatus;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.lieague.carwash.model.BookingStatus.*;

@ExtendWith(MockitoExtension.class)
class StatusAfterActiveValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private StatusAfterActiveValidator statusAfterActiveValidator;

    @Test
    @DisplayName("валидация валидного статуса")
    void isValid_WhenStatusValid_ThenReturnTrue() {
        boolean answer = statusAfterActiveValidator.isValid(PAID, context);
        assertTrue(answer);
    }

    @Test
    @DisplayName("валидация невалидного статуса")
    void isValid_WhenStatusNotValid_ThenReturnFalse() {
        boolean answer = statusAfterActiveValidator.isValid(NOT_CONFIRMED, context);
        assertFalse(answer);
    }
}