package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.BookingStatus;
import ru.lieague.carwash.model.entity.*;
import ru.lieague.carwash.model.filter.BookingFilter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;

public class BookingSpecification {

    public static Specification<Booking> generateSpecification(BookingFilter bookingFilter) {
        return bookingFilter != null
                ?
                where(findBookingByDay(bookingFilter.getWashingDate()))
                        .and(findByBookingStatus(bookingFilter.getBookingStatus()))
                        .and(findByCostGreaterThan(bookingFilter.getCostGreaterThan()))
                        .and(findByCostLessThan(bookingFilter.getCostLessThan()))
                        .and(findByBoxId(bookingFilter.getBoxId()))
                        .and(findByUserId(bookingFilter.getUserId()))
                        .and(findByCarWashingId(bookingFilter.getCarWashServiceId()))
                :
                where(null);
    }

    public static Specification<Booking> findBookingByDay(LocalDate day) {
        if (day == null) return null;
        return (root, query, criteriaBuilder) -> {
            Expression<LocalDate> dayExpression = criteriaBuilder.literal(day);
            Expression<Date> timestampToDateExpression = criteriaBuilder.function(
                    "date",
                    Date.class,
                    root.<Timestamp>get(Booking_.WASHING_START_TIME));
            return criteriaBuilder.equal(dayExpression, timestampToDateExpression);
        };
    }

    public static Specification<Booking> findByBookingStatus(BookingStatus bookingStatus) {
        if (bookingStatus == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Booking_.BOOKING_STATUS), bookingStatus);
    }

    public static Specification<Booking> findByCostGreaterThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get(Booking_.COST), value);
    }

    public static Specification<Booking> findByCostLessThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(Booking_.COST), value);
    }

    public static Specification<Booking> findByUserId(Long userId) {
        if (userId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<Booking, User> users = root.join(Booking_.user);
            return criteriaBuilder.equal(users.get(User_.ID), userId);
        };
    }

    public static Specification<Booking> findByBoxId(Long boxId) {
        if (boxId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<Booking, Box> boxes = root.join(Booking_.box);
            return criteriaBuilder.equal(boxes.get(Box_.ID), boxId);
        };
    }

    public static Specification<Booking> findByCarWashingId(Long carWashingId) {
        if (carWashingId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<Booking, CarWashService> carWashServices = root.join(Booking_.carWashService);
            return criteriaBuilder.equal(carWashServices.get(CarWashService_.ID), carWashingId);
        };
    }
}
