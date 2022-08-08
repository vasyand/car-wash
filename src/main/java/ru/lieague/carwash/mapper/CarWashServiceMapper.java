package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.entity.CarWashService;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING)
public interface CarWashServiceMapper {
    CarWashServiceFullDto carWashServiceToCarWashServiceFullDto(CarWashService carWashService);

    CarWashService carWashServiceCreateDtoToCarWashService(CarWashServiceCreateDto carWashServiceCreateDto);

    @Mapping(target = "duration", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "cost", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "carWashServiceType", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "description", nullValuePropertyMappingStrategy = IGNORE)
    CarWashService carWashServiceUpdateMergeWithCarWashService(CarWashServiceUpdateDto carWashServiceUpdateDto,
                                                              @MappingTarget CarWashService carWashService);
}
