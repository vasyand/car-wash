package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lieague.carwash.model.entity.CarWashService;

public interface CarWashServiceRepository extends JpaRepository<CarWashService, Long> {
}
