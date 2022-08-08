package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingCreateDto {
    private Long userId;
    private Long carWashServiceId;
    private LocalDateTime bookingTime;
}
