package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateDto {
    @NotNull
    private LocalDateTime bookingTime;
    @NotNull
    private Long carWashServiceId;
}
