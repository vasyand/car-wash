package ru.lieague.carwash.model.dto.discount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DiscountSetMaxDto {
    @NotBlank
    @Max(1)
    private Double max;
}
