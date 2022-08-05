package ru.lieague.carwash.model.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingCreateDto {
    private Long userId;
    private Long carWashServiceId;
    private LocalDateTime bookingTime;
}
