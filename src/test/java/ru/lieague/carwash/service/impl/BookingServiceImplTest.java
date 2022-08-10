package ru.lieague.carwash.service.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lieague.carwash.model.TimeInterval;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceFullDto;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Box;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    private static final List<Booking> TWO_BOOKINGS_IN_ONE_DAY = new ArrayList<>();
    private static final CarWashServiceFullDto CAR_WASH_SERVICE = new CarWashServiceFullDto();

    @BeforeAll
    static void init() {
        Box box = new Box();
        box.setCoefficient(2d);

        CAR_WASH_SERVICE.setDuration(15);

        Booking booking1 = new Booking();
        booking1.setWashingStartTime(LocalDateTime.of(2022, 8, 5, 13, 40));
        booking1.setWashingEndTime(LocalDateTime.of(2022, 8, 5, 16, 40));
        booking1.setBox(box);

        Booking booking2 = new Booking();
        booking2.setWashingStartTime(LocalDateTime.of(2022, 8, 5, 18, 40));
        booking2.setWashingEndTime(LocalDateTime.of(2022, 8, 5, 19, 40));
        booking2.setBox(box);

        TWO_BOOKINGS_IN_ONE_DAY.add(booking1);
        TWO_BOOKINGS_IN_ONE_DAY.add(booking2);
    }

    @Test
    @DisplayName("проверка создания интервалов при условии, что в этот день в бд будет две брони")
    void createFreeTimeIntervals_WhenTwoBookingsInOneDay_ThenReturnThreeTimeIntervals() {
        List<TimeInterval> intervals = bookingService
                .createFreeTimeIntervals(TWO_BOOKINGS_IN_ONE_DAY, CAR_WASH_SERVICE.getDuration());
        assertEquals(intervals.size(), 3);
    }

    @Test
    @DisplayName("проверка создания интервалов при условии, что в этот день в бд вообще брони нет")
    void createFreeTimeIntervals_WhenBookingsIsEmpty_ThenReturnFullTimeInterval() {
        List<TimeInterval> intervals = bookingService
                .createFreeTimeIntervals(new ArrayList<>(), CAR_WASH_SERVICE.getDuration());
        assertEquals(intervals.size(), 1);
    }

}