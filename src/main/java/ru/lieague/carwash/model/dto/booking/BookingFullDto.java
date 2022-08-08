package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.BookingStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingFullDto {
    private Long id;
    private LocalDateTime washingStartTime;
    private LocalDateTime washingEndTime;
    private Double cost;
    private BookingStatus bookingStatus;
    private Long userId;
    private Long boxId;
    private Long carWashServiceId;
}
