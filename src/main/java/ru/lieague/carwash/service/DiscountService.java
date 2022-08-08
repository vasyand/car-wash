package ru.lieague.carwash.service;

import ru.lieague.carwash.model.dto.discount.DiscountFullDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMaxDto;
import ru.lieague.carwash.model.dto.discount.DiscountSetMinDto;

public interface DiscountService {
    DiscountFullDto setMin(DiscountSetMinDto discountSetMinDto);

    DiscountFullDto setMax(DiscountSetMaxDto discountSetMaxDto);
    DiscountFullDto get();
}
