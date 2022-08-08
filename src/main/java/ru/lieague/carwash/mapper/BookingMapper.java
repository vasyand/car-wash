package ru.lieague.carwash.mapper;

import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.dto.BookingFullDto;
import ru.lieague.carwash.model.dto.BoxFullDto;
import ru.lieague.carwash.model.entity.Booking;


public interface BookingMapper {

    Booking bookingCreateDtoToBooking(BookingCreateDto bookingCreateDto);

    BookingFullDto bookingToBookingFullDto(Booking booking);
}
