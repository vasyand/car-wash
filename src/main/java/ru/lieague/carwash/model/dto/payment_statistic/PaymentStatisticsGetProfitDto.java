package ru.lieague.carwash.model.dto.payment_statistic;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentStatisticsGetProfitDto {
    @NotBlank
    private LocalDate from;
    @NotBlank
    private LocalDate to;
}
