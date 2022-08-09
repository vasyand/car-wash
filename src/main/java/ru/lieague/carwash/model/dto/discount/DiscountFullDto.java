package ru.lieague.carwash.model.dto.discount;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DiscountFullDto {
    private Double maxDiscount;
    private Double minDiscount;
}
