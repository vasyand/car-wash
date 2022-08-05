package ru.lieague.carwash.service;

import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.entity.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(Long carWashServiceId, LocalDate day);
    Booking creteBooking(BookingCreateDto bookingCreateDto);
}
