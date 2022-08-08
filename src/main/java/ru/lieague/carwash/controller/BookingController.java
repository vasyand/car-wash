package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.dto.BookingFullDto;
import ru.lieague.carwash.model.dto.BookingUpdateDto;
import ru.lieague.carwash.model.filter.BookingFilter;
import ru.lieague.carwash.service.BookingService;

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

    @ResponseStatus(CREATED)
    @PostMapping
    public BookingFullDto create(@RequestBody BookingCreateDto bookingCreateDto) {
        return bookingService.create(bookingCreateDto);
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
