package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.lieague.carwash.model.dto.payment_statistic.PaymentStatisticDto;
import ru.lieague.carwash.model.entity.PaymentStatistic;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PaymentStatisticMapper {

    @Mapping(target = "carWashServiceId", source = "paymentStatistic.carWashService.id")
    @Mapping(target = "userId", source = "paymentStatistic.user.id")
    @Mapping(target = "boxId", source = "paymentStatistic.box.id")
    PaymentStatisticDto paymentStatisticToPaymentStatisticDto(PaymentStatistic paymentStatistic);

}
