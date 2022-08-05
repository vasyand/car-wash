package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.Constants;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Booking_;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.repository.BookingRepository;
import ru.lieague.carwash.service.BookingService;
import ru.lieague.carwash.service.BoxService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import static ru.lieague.carwash.Constants.*;
import static ru.lieague.carwash.specification.BookingSpecification.findBookingByDay;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private BoxService boxService;

    @Override
    @Transactional
    public List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(CarWashService carWashService, LocalDate day) {
        List<Booking> bookings = bookingRepository.findAll(findBookingByDay(day), Sort.by(Booking_.WASHING_START_TIME));
        LinkedList<TimeInterval> intervals = new LinkedList<>();
        LocalTime startIntervalTime = LocalTime.of(WORKING_DAY_START_HOUR, 0);
        for (Booking booking : bookings) {
            LocalTime startTime = booking.getWashingStartTime().toLocalTime();
            long washServiceDuration = Math.round(carWashService.getDuration() * booking.getBox().getCoefficient());
            LocalTime startTimeMinusDuration = startTime.minusMinutes(washServiceDuration);
            if (startTimeMinusDuration.isBefore(startIntervalTime)) continue;
            intervals.add(new TimeInterval(startIntervalTime, startTimeMinusDuration));
            startIntervalTime = booking.getWashingEndTime().toLocalTime();
        }
        LocalTime endIntervalTime = LocalTime.of(WORKING_DAY_END_HOUR, 0);
        intervals.add(new TimeInterval(startIntervalTime, endIntervalTime));
        return intervals;
    }

    @Override
    @Transactional
    public Booking creteBooking(CarWashService carWashService, LocalDateTime time) {
        Box box = boxService.findTheBestBoxForCarWashServiceAtTime(carWashService, time);
        Booking booking = new Booking();
        booking.setBox(box);
        booking.setBookingStatus(BookingStatus.NOT_CONFIRMED);
        booking.setCarWashService(carWashService);
        booking.setCost(carWashService.getCost());
        booking.setUser(null); //TODO
        booking.setWashingStartTime(time);
        booking.setWashingEndTime(time.plusMinutes(Math.round(box.getCoefficient() * carWashService.getDuration())));
        bookingRepository.save(booking);
        return bookingRepository.save(booking);
    }
}

