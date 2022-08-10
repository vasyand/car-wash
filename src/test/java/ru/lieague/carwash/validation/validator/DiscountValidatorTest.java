package ru.lieague.carwash.validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lieague.carwash.model.dto.discount.DiscountFullDto;
import ru.lieague.carwash.service.DiscountService;

import javax.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountValidatorTest {

    private final static DiscountFullDto DISCOUNTS = new DiscountFullDto(0.15, 0.02);
    @Mock
    private DiscountService discountService;

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private DiscountValidator validator;

    @Test
    @DisplayName("валидация введенной скидки с приемлемым значением")
    void isValid_WhenDiscountBetweenMaxAndMin_ThenReturnTrue() {
        double okDiscount = 0.13;
        when(discountService.get()).thenReturn(DISCOUNTS);
        boolean answer = validator.isValid(okDiscount, context);
        assertTrue(answer);
    }

    @Test
    @DisplayName("валидация введенной скидки с неприемлемым значением")
    void isValid_WhenDiscountNotBetweenMaxAndMin_ThenReturnFalse() {
        double badDiscount = 0.25;
        when(discountService.get()).thenReturn(DISCOUNTS);
        boolean answer = validator.isValid(badDiscount, context);
        assertFalse(answer);
    }
}