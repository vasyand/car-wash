package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.lieague.carwash.model.entity.PaymentStatistic;

import java.util.Optional;

interface PaymentStatisticRepository extends JpaRepository<PaymentStatistic, Long> {

    Optional<Double> getProfitBetweenDate();
}
