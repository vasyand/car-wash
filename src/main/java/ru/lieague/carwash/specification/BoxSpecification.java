package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.Box_;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.entity.User_;
import ru.lieague.carwash.model.filter.BoxFilter;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import java.time.LocalTime;

import static org.springframework.data.jpa.domain.Specification.where;

public class BoxSpecification {
    public static Specification<Box> generateSpecification(BoxFilter boxFilter) {
        return boxFilter != null
                ?
                where(findBoxWorkingAtThisTime(boxFilter.getWorkingTime()))
                        .and(findByCoefficientGreaterThan(boxFilter.getCoefficientGreaterThan()))
                        .and(findByCoefficientLessThan(boxFilter.getCoefficientLessThan()))
                        .and(findByName(boxFilter.getName()))
                        .and(findByOperatorId(boxFilter.getOperatorId()))
                :
                where(null);
    }

    public static Specification<Box> findBoxWorkingAtThisTime(LocalTime time) {
        if (time == null) return null;
        return (root, query, criteriaBuilder) -> {
            Expression<LocalTime> timeExpression = criteriaBuilder.literal(time);
            return criteriaBuilder.between(
                    timeExpression,
                    root.get(Box_.START_WORKING),
                    root.get(Box_.END_WORKING)
            );
        };
    }

    public static Specification<Box> findByName(String name) {
        if (name == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Box_.NAME), "%" + name + "%");
    }

    public static Specification<Box> findByCoefficientGreaterThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get(Box_.COEFFICIENT), value);
    }

    public static Specification<Box> findByCoefficientLessThan(Double value) {
        if (value == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThan(root.get(Box_.COEFFICIENT), value);
    }

    public static Specification<Box> findByOperatorId(Long operatorId) {
        if (operatorId == null) return null;
        return (root, query, criteriaBuilder) -> {
            Join<Box, User> users = root.join(Box_.operator);
            return criteriaBuilder.equal(users.get(User_.ID), operatorId);
        };
    }

}
