package ru.lieague.carwash.model.dto.payment_statistic;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentStatisticDto {
    private Long id;
    private LocalDateTime paymentTime;
    private Double washingCost;
    private Long boxId;
    private Long carWashServiceId;
    private Long userId;
}
