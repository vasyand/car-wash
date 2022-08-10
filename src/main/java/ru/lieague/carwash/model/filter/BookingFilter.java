package ru.lieague.carwash.model.filter;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.BookingStatus;

import java.time.LocalDate;

@Getter
@Setter
public class BookingFilter {
    private LocalDate washingDate;
    private BookingStatus bookingStatus;
    private Double costGreaterThan;
    private Double costLessThan;
    private Long userId;
    private Long boxId;
    private Long carWashServiceId;
}
