package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.dto.BookingFullDto;
import ru.lieague.carwash.model.dto.BookingUpdateDto;
import ru.lieague.carwash.model.filter.BookingFilter;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    BookingFullDto findById(Long id);

    Page<BookingFullDto> findAll(Pageable pageable, BookingFilter bookingFilter);

    BookingFullDto update(BookingUpdateDto bookingUpdateDto, Long id);

    Long delete(Long id);

    List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(Long carWashServiceId, LocalDate day);
    BookingFullDto create(BookingCreateDto bookingCreateDto);
}
