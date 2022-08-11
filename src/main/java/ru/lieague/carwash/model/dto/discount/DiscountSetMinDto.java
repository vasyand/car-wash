package ru.lieague.carwash.model.dto.discount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DiscountSetMinDto {
    @NotNull
    @Min(0)
    private Double min;
}
