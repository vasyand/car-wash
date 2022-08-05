package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Booking_;

import javax.persistence.criteria.Expression;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class BookingSpecification {
    public static Specification<Booking> findBookingByDay(LocalDate day) {
        return (root, query, criteriaBuilder) -> {
            Expression<LocalDate> dayExpression = criteriaBuilder.literal(day);
            Expression<Date> timestampToDateExpression = criteriaBuilder.function(
                    "date",
                    Date.class,
                    root.<Timestamp>get(Booking_.WASHING_START_TIME));
            return criteriaBuilder.equal(dayExpression, timestampToDateExpression);
        };
    }
}
