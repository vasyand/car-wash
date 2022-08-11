package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.booking.*;
import ru.lieague.carwash.model.filter.BookingFilter;
import ru.lieague.carwash.service.BookingService;

import javax.validation.Valid;
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
    @PreAuthorize("@bookingControllerAccessValidator.thisBookingBelongToCurrentUser(#id)")
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
            @RequestBody @Valid BookingGetFreeTimesDto bookingGetFreeTimesDto
            ) {
        return bookingService.getBookingFreeTimeIntervalsForCarWashServiceOnDay(bookingGetFreeTimesDto);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public BookingFullDto create(@RequestBody @Valid BookingCreateDto bookingCreateDto) {
        return bookingService.save(bookingCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    @PreAuthorize("@bookingControllerAccessValidator.thisBookingIsUpdatingCurrentUser(#id)")
    public BookingFullDto update(@RequestBody @Valid BookingUpdateDto bookingUpdateDto,
                                    @PathVariable Long id) {
        return bookingService.update(bookingUpdateDto, id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}/confirm")
    @PreAuthorize("@bookingControllerAccessValidator.thisBookingIsConfirmingCurrentUser(#bookingConfirmStatusDto, #id)")
    public BookingFullDto confirm(@RequestBody @Valid BookingConfirmStatusDto bookingConfirmStatusDto,
                                             @PathVariable Long id) {
        return bookingService.confirmBooking(id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}/update-status")
    @PreAuthorize("@bookingControllerAccessValidator.canTheCurrentUserChangeBookingStatus(#id)")
    public BookingFullDto updateActiveStatus(@RequestBody @Valid BookingChangeStatusDto bookingChangeStatusDto,
                                             @PathVariable Long id) {
        return bookingService.changeStatus(bookingChangeStatusDto, id);
    }


    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return bookingService.delete(id);
    }
}
