package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.booking.*;
import ru.lieague.carwash.model.filter.BookingFilter;
import ru.lieague.carwash.service.BookingService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bookings")
public class BookingController {
    private final BookingService bookingService;

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public BookingFullDto findById(@PathVariable Long id) {
        return bookingService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<BookingFullDto> findAll(Pageable pageable, BookingFilter bookingFilter) {
        return bookingService.findAll(pageable, bookingFilter);
    }

    @ResponseStatus(OK)
    @GetMapping("/info/free-time")
    public List<TimeInterval> getBookingFreeTimeIntervalsForCarWashServiceOnDay(
            @RequestBody BookingGetFreeTimesDto bookingGetFreeTimesDto
            ) {
        return bookingService.getBookingFreeTimeIntervalsForCarWashServiceOnDay(bookingGetFreeTimesDto);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public BookingFullDto create(@RequestBody BookingCreateDto bookingCreateDto) {
        return bookingService.save(bookingCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public BookingFullDto update(@RequestBody BookingUpdateDto bookingUpdateDto,
                                    @PathVariable Long id) {
        return bookingService.update(bookingUpdateDto, id);
    }


    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return bookingService.delete(id);
    }
}
