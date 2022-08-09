package ru.lieague.carwash.service.job;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.repository.BookingRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoCancelBookingJob {
    @Value("${booking.minutes-before-start-washing-if-user-has-not-confirmed-booking}")
    private Integer minutesBeforeStartWashingIfUserHasNotConfirmedBooking;

    @Value("${booking.minutes-after-creating-booking-without-confirming}")
    private Integer minutesAfterCreatingBookingWithoutConfirming;
    private final BookingRepository bookingRepository;

    @Scheduled(fixedRateString = "${job.rate-for-check-booking-job}", initialDelay = 33333)
    @Transactional
    public void cancelBookingIfItHasNotBeenConfirmed() {
        List<Booking> bookingsForCancelling = bookingRepository.getCandidatesForCancelling(LocalDateTime.now(),
                minutesBeforeStartWashingIfUserHasNotConfirmedBooking,
                minutesAfterCreatingBookingWithoutConfirming);
        bookingsForCancelling
                .forEach(booking -> booking.setBookingStatus(BookingStatus.CANCELED));
    }


}
