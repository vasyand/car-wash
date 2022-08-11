package ru.lieague.carwash.model.dto.payment_statistic;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PaymentStatisticsGetProfitDto {
    @NotNull
    private LocalDate from;
    @NotNull
    private LocalDate to;
}
