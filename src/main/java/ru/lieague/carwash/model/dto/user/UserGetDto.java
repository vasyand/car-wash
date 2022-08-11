package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.dto.booking.BookingFullDto;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticDto;

import java.util.List;

@Getter
@Setter
public class UserGetDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Double discount;
    private List<BookingFullDto> bookingList;
    private List<PaymentStatisticDto> paymentStatistics;
}
