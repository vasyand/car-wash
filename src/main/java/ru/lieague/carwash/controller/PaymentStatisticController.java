package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticDto;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticsGetProfitDto;
import ru.lieague.carwash.model.filter.PaymentStatisticFilter;
import ru.lieague.carwash.service.PaymentStatisticService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment-statistics")
public class PaymentStatisticController {
    private final PaymentStatisticService paymentStatisticService;

    @ResponseStatus(OK)
    @GetMapping
    public Page<PaymentStatisticDto> findAll(@RequestBody PaymentStatisticFilter paymentStatisticFilter,
                                             Pageable pageable) {
        return paymentStatisticService.findAll(pageable, paymentStatisticFilter);
    }

    @ResponseStatus(OK)
    @GetMapping("/profit")
    public Double findAll(@RequestBody PaymentStatisticsGetProfitDto paymentStatisticsGetProfitDto) {
        return paymentStatisticService.getProfitBetweenDates(paymentStatisticsGetProfitDto);
    }

}
