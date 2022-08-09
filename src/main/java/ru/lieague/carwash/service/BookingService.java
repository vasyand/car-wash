package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.booking.*;
import ru.lieague.carwash.model.filter.BookingFilter;

import java.util.List;

public interface BookingService {

    BookingFullDto findById(Long id);

    Page<BookingFullDto> findAll(Pageable pageable, BookingFilter bookingFilter);

    BookingFullDto update(BookingUpdateDto bookingUpdateDto, Long id);

    Long delete(Long id);

    BookingFullDto confirmBooking(Long id);

    BookingFullDto changeStatus(BookingChangeStatusDto bookingChangeStatusDto, Long id);

    List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(BookingGetFreeTimesDto bookingGetFreeTimesDto);
    BookingFullDto save(BookingCreateDto bookingCreateDto);
}
