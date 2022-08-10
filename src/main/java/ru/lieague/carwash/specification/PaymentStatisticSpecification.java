package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.entity.*;
import ru.lieague.carwash.model.filter.PaymentStatisticFilter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import static org.springframework.data.jpa.domain.Specification.where;

public class PaymentStatisticSpecification {

    public static Specification<PaymentStatistic> generateSpecification(PaymentStatisticFilter paymentStatisticFilter) {
        return paymentStatisticFilter != null
                ?
                where(findBookingByDay(paymentStatisticFilter.getPaymentDate()))
                        .and(findByCostGreaterThan(paymentStatisticFilter.getCostGreaterThan()))
                        .and(findByCostLessThan(paymentStatisticFilter.getCostLessThan()))
                        .and(findByBoxId(paymentStatisticFilter.getBoxId()))
                        .and(findByUserId(paymentStatisticFilter.getUserId()))
                        .and(findByCarWashingId(paymentStatisticFilter.getCarWashServiceId()))
                :
                where(null);
    }

    public static Specification<PaymentStatistic> findBookingByDay(LocalDate day) {
        if (day == null) return null;
        return (root, query, criteriaBuilder) -> {
            Expression<LocalDate> dayExpression = criteriaBuilder.literal(day);
            Expression<Date> timestampToDateExpression = criteriaBuilder.function(
                    "date",
                    Date.class,
                    root.<Timestamp>get(PaymentStatistic_.PAYMENT_TIME));
            return criteriaBuilder.equal(dayExpression, timestampToDateExpression);
        };
    }

    public static Specification<PaymentStatistic> findByCostGreaterThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(PaymentStatistic_.WASHING_COST), value);
    }

    public static Specification<PaymentStatistic> findByCostLessThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(PaymentStatistic_.WASHING_COST), value);
    }

    public static Specification<PaymentStatistic> findByUserId(Long userId) {
        if (userId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<PaymentStatistic, User> users = root.join(PaymentStatistic_.user);
            return criteriaBuilder.equal(users.get(User_.ID), userId);
        };
    }

    public static Specification<PaymentStatistic> findByBoxId(Long boxId) {
        if (boxId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<PaymentStatistic, Box> boxes = root.join(PaymentStatistic_.box);
            return criteriaBuilder.equal(boxes.get(Box_.ID), boxId);
        };
    }

    public static Specification<PaymentStatistic> findByCarWashingId(Long carWashingId) {
        if (carWashingId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<PaymentStatistic, CarWashService> carWashServices = root.join(PaymentStatistic_.carWashService);
            return criteriaBuilder.equal(carWashServices.get(CarWashService_.ID), carWashingId);
        };
    }
}
