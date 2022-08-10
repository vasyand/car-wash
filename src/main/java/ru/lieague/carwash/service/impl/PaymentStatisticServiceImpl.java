package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.mapper.PaymentStatisticMapper;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticDto;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticsGetProfitDto;
import ru.lieague.carwash.model.entity.PaymentStatistic;
import ru.lieague.carwash.model.filter.PaymentStatisticFilter;
import ru.lieague.carwash.repository.PaymentStatisticRepository;
import ru.lieague.carwash.service.PaymentStatisticService;
import ru.lieague.carwash.specification.PaymentStatisticSpecification;

import static ru.lieague.carwash.specification.PaymentStatisticSpecification.*;

@Service
@RequiredArgsConstructor
public class PaymentStatisticServiceImpl implements PaymentStatisticService {
    private final PaymentStatisticRepository paymentStatisticRepository;
    private final PaymentStatisticMapper paymentStatisticMapper;

    @Override
    public Double getProfitBetweenDates(PaymentStatisticsGetProfitDto paymentStatisticsGetProfitDto) {
        return paymentStatisticRepository.getProfitBetweenDate(
                paymentStatisticsGetProfitDto.getFrom(),
                paymentStatisticsGetProfitDto.getTo()
        ).orElse(0d);
    }

    @Override
    public Page<PaymentStatisticDto> findAll(Pageable pageable, PaymentStatisticFilter paymentStatisticFilter) {
        return paymentStatisticRepository.findAll(generateSpecification(paymentStatisticFilter), pageable)
                .map(paymentStatisticMapper::paymentStatisticToPaymentStatisticDto);
    }

    @Override
    public void save(PaymentStatistic paymentStatistic) {
        paymentStatisticRepository.save(paymentStatistic);
    }
}
