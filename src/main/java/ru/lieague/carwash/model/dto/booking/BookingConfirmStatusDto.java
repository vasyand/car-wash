package ru.lieague.carwash.model.dto.booking;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BookingConfirmStatusDto {
    @NotNull
    private Long userId;
}
