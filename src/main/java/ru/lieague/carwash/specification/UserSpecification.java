package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.entity.Booking;
import ru.lieague.carwash.model.entity.Booking_;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.entity.User_;
import ru.lieague.carwash.model.filter.UserFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.lieague.carwash.model.BookingStatus.*;

public class UserSpecification {

    public static Specification<User> generateSpecification(UserFilter userFilter) {
        return userFilter != null
                ?
                where(findByEmail(userFilter.getEmail()))
                        .and(findByFirstName(userFilter.getFirstName()))
                        .and(findByMiddleName(userFilter.getMiddleName()))
                        .and(findByLastName(userFilter.getLastName()))
                :
                where(null);
    }

    public static Specification<User> findByFirstName(String name) {
        if (name == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(User_.FIRST_NAME), "%" + name + "%");
    }

    public static Specification<User> findByMiddleName(String name) {
        if (name == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(User_.MIDDLE_NAME), "%" + name + "%");
    }

    public static Specification<User> findByLastName(String name) {
        if (name == null) return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(User_.LAST_NAME), "%" + name + "%");
    }

    public static Specification<User> findByEmail(String email) {
        if (email == null) return null;
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.EMAIL), email);
    }


}
