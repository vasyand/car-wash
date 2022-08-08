package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;

public interface CarWashServiceService {
    CarWashServiceFullDto findById(Long id);

    Page<CarWashServiceFullDto> findAll(Pageable pageable, CarWashServiceFilter carWashServiceFilter);

    CarWashServiceFullDto save(CarWashServiceCreateDto carWashServiceCreateDto);

    CarWashServiceFullDto update(CarWashServiceUpdateDto carWashServiceUpdateDto, Long id);

    Long delete(Long id);
}
