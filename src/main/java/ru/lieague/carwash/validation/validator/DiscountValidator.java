package ru.lieague.carwash.validation.validator;

import lombok.RequiredArgsConstructor;
import ru.lieague.carwash.model.dto.discount.DiscountFullDto;
import ru.lieague.carwash.service.DiscountService;
import ru.lieague.carwash.validation.annotation.Discount;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.lang.Double.*;

@RequiredArgsConstructor
public class DiscountValidator implements ConstraintValidator<Discount, Double> {
    private final DiscountService discountService;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        DiscountFullDto discountFullDto = discountService.get();
        return compare(value, discountFullDto.getMinDiscount()) > 0
                && compare(value, discountFullDto.getMaxDiscount()) < 0;
    }

}
