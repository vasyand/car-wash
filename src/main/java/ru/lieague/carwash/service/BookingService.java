package ru.lieague.carwash.service;

import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.CarWashService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(CarWashService carWashService, LocalDate day);
    Booking creteBooking(CarWashService carWashService, LocalDateTime time);
}
