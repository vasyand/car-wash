package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingGetFreeTimesDto {
    private Long carWashServiceId;
    private LocalDate day;
}
