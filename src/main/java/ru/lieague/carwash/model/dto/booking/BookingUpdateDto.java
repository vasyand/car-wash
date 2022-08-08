package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateDto {
    private LocalDateTime bookingTime;
    private Long carWashServiceId;
}
