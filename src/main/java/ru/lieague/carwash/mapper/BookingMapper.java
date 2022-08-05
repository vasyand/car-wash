package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import ru.lieague.carwash.model.dto.BookingCreateDto;
import ru.lieague.carwash.model.entity.Booking;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BookingMapper {

    Booking bookingCreateDtoToBooking(BookingCreateDto bookingCreateDto);
}
