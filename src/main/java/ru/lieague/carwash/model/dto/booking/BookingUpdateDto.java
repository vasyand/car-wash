package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookingUpdateDto {
    @NotBlank
    private LocalDateTime bookingTime;
    @NotBlank
    private Long carWashServiceId;
}
