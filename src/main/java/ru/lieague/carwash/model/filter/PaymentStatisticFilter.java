package ru.lieague.carwash.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PaymentStatisticFilter {
    private LocalDate paymentDate;
    private Double costGreaterThan;
    private Double costLessThan;
    private Long boxId;
    private Long carWashServiceId;
    private Long userId;
}
