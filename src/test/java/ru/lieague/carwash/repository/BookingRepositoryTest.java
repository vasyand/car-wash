package ru.lieague.carwash.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.lieague.carwash.model.entity.Booking;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookingRepositoryTest {
    private final static LocalDateTime TEST_DATE = LocalDateTime.of(2022, 8, 15, 13, 20);
    private final static int MINUTES_BEFORE_START_WASHING_IF_USER_HAS_NOT_CONFIRMED_BOOKING = 15;
    private final static int MINUTES_AFTER_CREATING_BOOKING_WITHOUT_CONFIRMING = 15;
    private final static int QUANTITY_OF_CANDIDATES_FOR_CANCELLING_AT_TEST_DATE = 2;
    @Autowired
    private BookingRepository bookingRepository;

    @Test
    @DisplayName("запрос на поиск броней, которые необходимо отменить")
    void getCandidatesForCancelling_Test() {
        List<Booking> bookings = bookingRepository.getCandidatesForCancelling(
                TEST_DATE,
                MINUTES_BEFORE_START_WASHING_IF_USER_HAS_NOT_CONFIRMED_BOOKING,
                MINUTES_AFTER_CREATING_BOOKING_WITHOUT_CONFIRMING
        );
        assertEquals(bookings.size(), QUANTITY_OF_CANDIDATES_FOR_CANCELLING_AT_TEST_DATE);
    }
}