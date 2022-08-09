package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lieague.carwash.model.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
    @Query(nativeQuery = true, value = "select * " +
            "from booking b " +
            "where (b.booking_status = 'ACTIVE'" +
            "    and :current_date_time" +
            "       between " +
            "         timestamp_mi_interval(b.washing_start_time, make_interval(mins \\:= :minutesBefore)) and b.washing_start_time)" +
            "or (b.booking_status = 'NOT_CONFIRMED'" +
            "    and :current_date_time > timestamp_pl_interval(b.create_date, make_interval(mins \\:= :minutesAfter)))")
    List<Booking> getCandidatesForCancelling(
            @Param("current_date_time") LocalDateTime currentDateTime,
            @Param("minutesBefore") Integer minutesBeforeStartWashingIfUserHasNotConfirmedBooking,
            @Param("minutesAfter") Integer minutesAfterCreatingBookingWithoutConfirming
    );
}
