package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.exception.FreeBoxNotFoundException;
import ru.lieague.carwash.mapper.BookingMapper;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.booking.*;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.user.UserGetDto;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Booking_;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.PaymentStatistic;
import ru.lieague.carwash.model.filter.BookingFilter;
import ru.lieague.carwash.repository.BookingRepository;
import ru.lieague.carwash.service.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;
import static java.lang.String.format;
import static ru.lieague.carwash.Constants.WORKING_DAY_END_HOUR;
import static ru.lieague.carwash.Constants.WORKING_DAY_START_HOUR;
import static ru.lieague.carwash.model.BookingStatus.*;
import static ru.lieague.carwash.specification.BookingSpecification.*;
import static ru.lieague.carwash.specification.BookingSpecification.findBookingByDay;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BoxService boxService;
    private final UserService userService;
    private final CarWashServiceService carWashServiceService;
    private final PaymentStatisticService paymentStatisticService;
    private final BookingMapper bookingMapper;

    @Override
    public BookingFullDto findById(Long id) {
        return bookingMapper.bookingToBookingFullDto(findBookingByIdOrThrowException(id));
    }

    @Override
    public Page<BookingFullDto> findAll(Pageable pageable, BookingFilter bookingFilter) {
        return bookingRepository.findAll(generateSpecification(bookingFilter), pageable)
                .map(bookingMapper::bookingToBookingFullDto);
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Booking booking = findBookingByIdOrThrowException(id);
        bookingRepository.delete(booking);
        return booking.getId();
    }


    private Booking findBookingByIdOrThrowException(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("?????????? ?? id %s ???? ????????????????????", id)));
    }

    @Override
    @Transactional
    public BookingFullDto changeStatus(BookingChangeStatusDto bookingChangeStatusDto, Long id) {
        Booking booking = findBookingByIdOrThrowException(id);
        booking.setBookingStatus(bookingChangeStatusDto.getBookingStatus());
        if (bookingChangeStatusDto.getBookingStatus() == PAID) {
            paymentStatisticService.save(new PaymentStatistic(
                    null,
                    LocalDateTime.now(),
                    booking.getCost(),
                    booking.getBox(),
                    booking.getCarWashService(),
                    booking.getUser()
            ));
        }
        return bookingMapper.bookingToBookingFullDto(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public BookingFullDto confirmBooking(Long id) {
        Booking booking = findBookingByIdOrThrowException(id);
        booking.setBookingStatus(ACTIVE);
        return bookingMapper.bookingToBookingFullDto(bookingRepository.save(booking));
    }

    @Override
    @Transactional
    public List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(BookingGetFreeTimesDto bookingGetFreeTimesDto) {
        List<Booking> bookings = getBookingsOnDay(bookingGetFreeTimesDto.getDay());
        CarWashServiceFullDto carWashService = carWashServiceService.findById(bookingGetFreeTimesDto.getCarWashServiceId());
        return createFreeTimeIntervals(bookings, carWashService.getDuration());
    }

    private List<Booking> getBookingsOnDay(LocalDate day) {
        return bookingRepository.findAll(
                findBookingByDay(day),
                Sort.by(Booking_.WASHING_START_TIME)
        );
    }

    public List<TimeInterval> createFreeTimeIntervals(List<Booking> bookings, int duration) {
        List<TimeInterval> intervals = new ArrayList<>();
        LocalTime startIntervalFreeTime = WORKING_DAY_START_HOUR;

        for (Booking booking : bookings) {
            LocalTime washingStartTime = booking.getWashingStartTime().toLocalTime();
            long washServiceDuration = round(duration * booking.getBox().getCoefficient());
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
    public BookingFullDto update(BookingUpdateDto bookingUpdateDto, Long id) {
        Booking oldBooking = findBookingByIdOrThrowException(id);
        oldBooking.setBookingStatus(CANCELED);
        bookingRepository.saveAndFlush(oldBooking);
        try {
            Booking newBooking = createBooking(bookingMapper.bookingUpdateDtoToBookingCreateDto(bookingUpdateDto));
            newBooking.setId(id);
            return bookingMapper.bookingToBookingFullDto(bookingRepository.save(newBooking));
        } catch (FreeBoxNotFoundException e) {
            oldBooking.setBookingStatus(ACTIVE);
            return bookingMapper.bookingToBookingFullDto(bookingRepository.save(oldBooking));
        }
    }

    @Override
    @Transactional
    public BookingFullDto save(BookingCreateDto bookingCreateDto) {
        Booking booking = createBooking(bookingCreateDto);
        return bookingMapper.bookingToBookingFullDto(bookingRepository.save(booking));
    }


    private Booking createBooking(BookingCreateDto bookingCreateDto) {
        CarWashServiceFullDto carWashServiceDto = carWashServiceService.findById(bookingCreateDto.getCarWashServiceId());
        UserGetDto userDto = userService.findById(bookingCreateDto.getUserId());
        BoxFullDto boxDto = boxService
                .findTheBestBoxForCarWashServiceAtTime(
                        carWashServiceDto.getDuration(), bookingCreateDto.getBookingTime()
                );

        Booking booking = bookingMapper.bookingCreateDtoToBooking(bookingCreateDto);

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
                .plusMinutes(round(boxDto.getCoefficient() * carWashServiceDto.getDuration()));
        booking.setWashingEndTime(washingEndTime);

        return booking;
    }
}

