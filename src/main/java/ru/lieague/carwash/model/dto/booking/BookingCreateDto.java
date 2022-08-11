package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingCreateDto {
    @NotNull
    private Long userId;

    @NotNull
    private Long carWashServiceId;

    @NotNull
    @Future
    private LocalDateTime bookingTime;
}
