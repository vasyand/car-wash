package ru.lieague.carwash.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.entity.User_;

public class UserSpecification {
    public static Specification<User> findByEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.EMAIL), email);
    }
}
