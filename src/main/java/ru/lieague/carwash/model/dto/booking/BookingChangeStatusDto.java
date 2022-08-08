package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.BookingStatus;

@Getter
@Setter
public class BookingChangeStatusDto {
    private BookingStatus bookingStatus;
}
