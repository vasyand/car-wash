package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.lieague.carwash.model.dto.booking.BookingCreateDto;
import ru.lieague.carwash.model.dto.booking.BookingFullDto;
import ru.lieague.carwash.model.dto.booking.BookingUpdateDto;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.entity.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;


@Mapper(componentModel = SPRING)
public interface BookingMapper {

    @Mapping(target = "washingStartTime", source = "bookingTime")
    @Mapping(target = "user", source = "userId", qualifiedByName = "createUserWithId")
    @Mapping(target = "carWashService", source = "carWashServiceId", qualifiedByName = "createCarWashServiceWithId")
    Booking bookingCreateDtoToBooking(BookingCreateDto bookingCreateDto);

    @Mapping(target = "carWashServiceId", source = "booking.carWashService.id")
    @Mapping(target = "userId", source = "booking.user.id")
    @Mapping(target = "boxId", source = "booking.box.id")
    BookingFullDto bookingToBookingFullDto(Booking booking);


    BookingCreateDto bookingUpdateDtoToBookingCreateDto(BookingUpdateDto bookingUpdateDto);

    @Mapping(target = "washingStartTime", source = "bookingTime", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "carWashService", source = "carWashServiceId",
            qualifiedByName = "createCarWashServiceWithId", nullValuePropertyMappingStrategy = IGNORE)
    Booking bookingUpdateDtoMergeWithBooking(BookingUpdateDto bookingUpdateDto, @MappingTarget Booking booking);

    @Named("createUserWithId")
    static User createUserWithId(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Named("createBoxWithId")
    static Box createBoxWithId(Long id) {
        Box box = new Box();
        box.setId(id);
        return box;
    }

    @Named("createCarWashServiceWithId")
    static CarWashService createCarWashServiceWithId(Long id) {
        CarWashService carWashService = new CarWashService();
        carWashService.setId(id);
        return carWashService;
    }
}
