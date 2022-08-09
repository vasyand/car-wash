package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.validation.annotation.Discount;

@Getter
@Setter
public class UserSetDiscountDto {
    @Discount
    private Double discount;
}
