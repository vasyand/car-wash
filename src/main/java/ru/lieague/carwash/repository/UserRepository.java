package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lieague.carwash.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
