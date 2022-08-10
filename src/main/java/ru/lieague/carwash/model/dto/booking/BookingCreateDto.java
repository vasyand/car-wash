package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingCreateDto {
    @NotBlank
    private Long userId;
    @NotBlank
    private Long carWashServiceId;
    @NotBlank
    @Future
    private LocalDateTime bookingTime;
}
