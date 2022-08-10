package ru.lieague.carwash.controller.access_control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lieague.carwash.model.UserRole;
import ru.lieague.carwash.model.dto.booking.BookingConfirmStatusDto;
import ru.lieague.carwash.model.dto.booking.BookingFullDto;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.user.UserSecurity;
import ru.lieague.carwash.service.BookingService;
import ru.lieague.carwash.service.BoxService;

import static ru.lieague.carwash.controller.access_control.CurrentUserUtil.getCurrentUser;
import static ru.lieague.carwash.model.BookingStatus.ACTIVE;
import static ru.lieague.carwash.model.BookingStatus.NOT_CONFIRMED;
import static ru.lieague.carwash.model.UserRole.*;

@Component
@RequiredArgsConstructor
public class BookingControllerAccessValidator {
    private final BookingService bookingService;

    private final BoxService boxService;

    public boolean thisBookingBelongToCurrentUser(Long id) {
        BookingFullDto bookingFullDto = bookingService.findById(id);
        Long userId = bookingFullDto.getUserId();
        UserSecurity user = getCurrentUser();
        return user.getRole() == ADMIN
                || user.getId().equals(userId);
    }

    public boolean thisBookingIsUpdatingCurrentUser(Long id) {
        BookingFullDto bookingFullDto = bookingService.findById(id);
        Long userId = bookingFullDto.getUserId();
        UserSecurity user = getCurrentUser();
        return user.getRole() == ADMIN
                || (user.getId().equals(userId)
                && bookingFullDto.getBookingStatus() == ACTIVE);
    }

    public boolean thisBookingIsConfirmingCurrentUser(BookingConfirmStatusDto bookingConfirmStatusDto, Long id) {
        BookingFullDto bookingFullDto = bookingService.findById(id);
        UserSecurity currentUser = getCurrentUser();
        return currentUser.getRole() == ADMIN
                || (currentUser.getId().equals(bookingConfirmStatusDto.getUserId())
                && bookingFullDto.getUserId().equals(bookingConfirmStatusDto.getUserId())
                && bookingFullDto.getBookingStatus() == NOT_CONFIRMED);
    }

    public boolean canTheCurrentUserChangeBookingStatus(Long id) {
        BookingFullDto booking = bookingService.findById(id);
        return booking.getBookingStatus() == ACTIVE
                && userCanChange(booking);
    }

    private boolean userCanChange(BookingFullDto booking) {
        UserSecurity user = getCurrentUser();
        UserRole role = user.getRole();
        BoxFullDto box = boxService.findById(booking.getBoxId());
        return role == ADMIN
                || (role == OPERATOR && box.getOperatorId().equals(user.getId()))
                || (role == USER && booking.getUserId().equals(user.getId()));
    }

}
