package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.UserRole;
import ru.lieague.carwash.model.dto.booking.BookingFullDto;

import java.util.List;

@Getter
@Setter
public class UserFullDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobile;
    private Double discount;
    private UserRole role;
    private List<BookingFullDto> bookingList;
}
