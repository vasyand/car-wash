package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticDto;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticsGetProfitDto;
import ru.lieague.carwash.model.entity.PaymentStatistic;
import ru.lieague.carwash.model.filter.PaymentStatisticFilter;

public interface PaymentStatisticService {
    Double getProfitBetweenDates(PaymentStatisticsGetProfitDto paymentStatisticsGetProfitDto);
    Page<PaymentStatisticDto> findAll(Pageable pageable, PaymentStatisticFilter paymentStatisticFilter);
    void save(PaymentStatistic paymentStatistic);
}
