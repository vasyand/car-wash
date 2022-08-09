package ru.lieague.carwash.controller.access_control;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.lieague.carwash.config.security.UserDetailsImpl;
import ru.lieague.carwash.model.UserRole;
import ru.lieague.carwash.model.dto.booking.BookingChangeStatusDto;
import ru.lieague.carwash.model.dto.booking.BookingConfirmStatusDto;
import ru.lieague.carwash.model.dto.booking.BookingFullDto;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.user.UserFullDto;
import ru.lieague.carwash.service.BookingService;
import ru.lieague.carwash.service.BoxService;

import static ru.lieague.carwash.controller.access_control.CurrentUserUtil.*;
import static ru.lieague.carwash.model.BookingStatus.ACTIVE;
import static ru.lieague.carwash.model.UserRole.*;

@Component
@RequiredArgsConstructor
public class BookingControllerAccessValidator {
    private final BookingService bookingService;

    private final BoxService boxService;

    public boolean thisBookingBelongToCurrentUser(Long id) {
        BookingFullDto bookingFullDto = bookingService.findById(id);
        Long userId = bookingFullDto.getUserId();
        UserFullDto user = getCurrentUser();
        return user.getRole() == ADMIN
                || user.getId().equals(userId);
    }

    public boolean thisBookingIsConfirmingCurrentUser(BookingConfirmStatusDto bookingConfirmStatusDto, Long id) {
        BookingFullDto bookingFullDto = bookingService.findById(id);
        Long userId = bookingFullDto.getUserId();
        UserFullDto user = getCurrentUser();
        return user.getRole() == ADMIN
                || user.getId().equals(bookingConfirmStatusDto.getUserId());
    }

    public boolean canTheCurrentUserChangeBookingStatus(Long id) {
        BookingFullDto booking = bookingService.findById(id);
        return booking.getBookingStatus() == ACTIVE
                && userCanChange(booking);
    }

    private boolean userCanChange(BookingFullDto booking) {
        UserFullDto user = getCurrentUser();
        UserRole role = user.getRole();
        BoxFullDto box = boxService.findById(booking.getBoxId());
        return role == ADMIN
                || (role == OPERATOR && box.getOperatorId().equals(user.getId()))
                || (role == USER && booking.getUserId().equals(user.getId()));
    }

}
