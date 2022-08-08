package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;

public interface CarWashServiceService {
    CarWashServiceFullDto findById(Long id);

    Page<CarWashServiceFullDto> findAll(Pageable pageable, CarWashServiceFilter carWashServiceFilter);

    CarWashServiceFullDto save(CarWashServiceCreateDto carWashServiceCreateDto);

    CarWashServiceFullDto update(CarWashServiceUpdateDto carWashServiceUpdateDto, Long id);

    Long delete(Long id);
}
