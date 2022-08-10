package ru.lieague.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.lieague.carwash.model.entity.PaymentStatistic;

import java.time.LocalDate;
import java.util.Optional;

public interface PaymentStatisticRepository extends JpaRepository<PaymentStatistic, Long>, JpaSpecificationExecutor<PaymentStatistic> {

    @Query(nativeQuery = true, value = "select sum(p.washing_cost) " +
            "from payment_statistics p " +
            "    where p.payment_time between :from and :to")
    Optional<Double> getProfitBetweenDate(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
