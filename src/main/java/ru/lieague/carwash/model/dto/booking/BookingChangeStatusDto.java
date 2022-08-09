package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.validation.annotation.StatusAfterActive;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class BookingChangeStatusDto {
    @NotBlank
    @StatusAfterActive
    private BookingStatus bookingStatus;
}
