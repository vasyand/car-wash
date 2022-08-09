package ru.lieague.carwash.model.dto.discount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;

@Getter
@Setter
public class DiscountSetMinDto {
    @Min(0)
    private Double min;
}
