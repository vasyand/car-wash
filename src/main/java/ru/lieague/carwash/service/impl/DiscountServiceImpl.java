package ru.lieague.carwash.service.impl;

import org.springframework.stereotype.Service;
import ru.lieague.carwash.model.dto.discount.DiscountFullDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMaxDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMinDto;
import ru.lieague.carwash.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService {

    @Override
    public DiscountFullDto setMin(DiscountSetMinDto discountSetMinDto) {
        DiscountValues.MIN_DISCOUNT = discountSetMinDto.getMin();
        return get();
    }

    @Override
    public DiscountFullDto setMax(DiscountSetMaxDto discountSetMaxDto) {
        DiscountValues.MAX_DISCOUNT = discountSetMaxDto.getMax();
        return get();
    }

    @Override
    public DiscountFullDto get() {
        return new DiscountFullDto(
                DiscountValues.MAX_DISCOUNT,
                DiscountValues.MIN_DISCOUNT
                );
    }

    private static class DiscountValues {
        static volatile double MAX_DISCOUNT = 0.15;
        static volatile double MIN_DISCOUNT = 0d;
    }
}
