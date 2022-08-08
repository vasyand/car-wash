package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.Constants;
import ru.lieague.carwash.mapper.BookingMapper;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.*;
import ru.lieague.carwash.model.entity.*;
import ru.lieague.carwash.model.filter.BookingFilter;
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
    public BookingFullDto findById(Long id) {
        return null;
    }

    @Override
    public Page<BookingFullDto> findAll(Pageable pageable, BookingFilter bookingFilter) {
        return null;
    }

    @Override
    public BookingFullDto update(BookingUpdateDto bookingUpdateDto, Long id) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    @Transactional
    public List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(Long carWashServiceId, LocalDate day) {
        List<Booking> bookings = bookingRepository.findAll(findBookingByDay(day), Sort.by(Booking_.WASHING_START_TIME));
        CarWashServiceFullDto carWashService = carWashServiceService.findById(carWashServiceId);
        List<TimeInterval> intervals = new ArrayList<>();
        LocalTime startIntervalFreeTime = WORKING_DAY_START_HOUR;

        for (Booking booking : bookings) {
            LocalTime washingStartTime = booking.getWashingStartTime().toLocalTime();
            long washServiceDuration = round(carWashService.getDuration() * booking.getBox().getCoefficient());
            LocalTime washingStartTimeMinusDuration = washingStartTime.minusMinutes(washServiceDuration);
            if (washingStartTimeMinusDuration.isAfter(startIntervalFreeTime)) {
                intervals.add(new TimeInterval(startIntervalFreeTime, washingStartTimeMinusDuration));
                startIntervalFreeTime = booking.getWashingEndTime().toLocalTime();
            }
        }

        intervals.add(new TimeInterval(startIntervalFreeTime, WORKING_DAY_END_HOUR));
        return intervals;
    }

    @Override
    @Transactional
    public BookingFullDto create(BookingCreateDto bookingCreateDto) {
        CarWashServiceFullDto carWashServiceDto = carWashServiceService.findById(bookingCreateDto.getCarWashServiceId());
        UserFullDto userDto = userService.findById(bookingCreateDto.getUserId());
        BoxFullDto boxDto = boxService
                .findTheBestBoxForCarWashServiceAtTime(
                        carWashServiceDto.getDuration(), bookingCreateDto.getBookingTime()
                );

        Booking booking = new Booking();

        CarWashService carWashServiceEntity = new CarWashService();
        carWashServiceEntity.setId(carWashServiceDto.getId());
        booking.setCarWashService(carWashServiceEntity);

        User user = new User();
        user.setId(userDto.getId());
        booking.setUser(user);

        Box boxEntity = new Box();
        boxEntity.setId(boxDto.getId());
        booking.setBox(boxEntity);

        booking.setBookingStatus(NOT_CONFIRMED);

        Double cost = carWashServiceDto.getCost();
        if (userDto.getDiscount() != null) {
            cost = cost - (cost * userDto.getDiscount());
        }
        booking.setCost(cost);

        LocalDateTime washingEndTime = bookingCreateDto.getBookingTime()
                .plusMinutes(round(boxEntity.getCoefficient() * carWashServiceDto.getDuration()));
        booking.setWashingStartTime(bookingCreateDto.getBookingTime());
        booking.setWashingEndTime(washingEndTime);

        return bookingMapper.bookingToBookingFullDto(bookingRepository.save(booking));
    }


}

