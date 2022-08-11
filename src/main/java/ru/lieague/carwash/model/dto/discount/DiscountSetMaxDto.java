package ru.lieague.carwash.model.dto.discount;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DiscountSetMaxDto {
    @NotNull
    @Max(1)
    private Double max;
}
