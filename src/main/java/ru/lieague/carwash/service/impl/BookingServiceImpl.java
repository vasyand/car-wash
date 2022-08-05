package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.Constants;
import ru.lieague.carwash.mapper.BookingMapper;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.entity.*;
import ru.lieague.carwash.repository.BookingRepository;
import ru.lieague.carwash.service.BookingService;
import ru.lieague.carwash.service.BoxService;
import ru.lieague.carwash.service.CarWashServiceService;
import ru.lieague.carwash.service.UserService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.*;
import static ru.lieague.carwash.Constants.*;
import static ru.lieague.carwash.model.BookingStatus.*;
import static ru.lieague.carwash.specification.BookingSpecification.findBookingByDay;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BoxService boxService;

    private final UserService userService;
    private final CarWashServiceService carWashServiceService;

    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(Long carWashServiceId, LocalDate day) {
        List<Booking> bookings = bookingRepository.findAll(findBookingByDay(day), Sort.by(Booking_.WASHING_START_TIME));
        CarWashService carWashService = carWashServiceService.findById(carWashServiceId);
        List<TimeInterval> intervals = new ArrayList<>();
        LocalTime startIntervalFreeTime = LocalTime.of(WORKING_DAY_START_HOUR, 0);

        for (Booking booking : bookings) {
            LocalTime washingStartTime = booking.getWashingStartTime().toLocalTime();
            long washServiceDuration = round(carWashService.getDuration() * booking.getBox().getCoefficient());
            LocalTime washingStartTimeMinusDuration = washingStartTime.minusMinutes(washServiceDuration);
            if (washingStartTimeMinusDuration.isAfter(startIntervalFreeTime)) {
                intervals.add(new TimeInterval(startIntervalFreeTime, washingStartTimeMinusDuration));
                startIntervalFreeTime = booking.getWashingEndTime().toLocalTime();
            }
        }

        LocalTime endIntervalFreeTime = LocalTime.of(WORKING_DAY_END_HOUR, 0);
        intervals.add(new TimeInterval(startIntervalFreeTime, endIntervalFreeTime));
        return intervals;
    }

    @Override
    @Transactional
    public Booking creteBooking(BookingCreateDto bookingCreateDto) {
        CarWashService carWashService = carWashServiceService.findById(bookingCreateDto.getCarWashServiceId());
        User user = userService.findById(bookingCreateDto.getUserId());
        Box box = boxService
                .findTheBestBoxForCarWashServiceAtTime(carWashService.getDuration(), bookingCreateDto.getBookingTime());

        Booking booking = new Booking();
        booking.setCarWashService(carWashService);
        booking.setUser(user);
        booking.setBox(box);
        booking.setBookingStatus(NOT_CONFIRMED);

        Double cost = carWashService.getCost();
        if (user.getDiscount() != null) {
            cost = cost - (cost * user.getDiscount());
        }
        booking.setCost(cost);

        LocalDateTime washingEndTime = bookingCreateDto.getBookingTime()
                .plusMinutes(round(box.getCoefficient() * carWashService.getDuration()));
        booking.setWashingStartTime(bookingCreateDto.getBookingTime());
        booking.setWashingEndTime(washingEndTime);

        return bookingRepository.save(booking);
    }
}

