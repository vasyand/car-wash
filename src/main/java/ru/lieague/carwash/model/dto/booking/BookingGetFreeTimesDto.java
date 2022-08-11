package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class BookingGetFreeTimesDto {
    @NotNull
    private Long carWashServiceId;
    @NotNull
    private LocalDate day;
}
