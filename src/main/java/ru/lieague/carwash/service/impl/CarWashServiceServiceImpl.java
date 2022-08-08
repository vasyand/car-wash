package ru.lieague.carwash.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.model.dto.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;
import ru.lieague.carwash.service.CarWashServiceService;

@Service
public class CarWashServiceServiceImpl implements CarWashServiceService {
    @Override
    public CarWashServiceFullDto findById(Long id) {
        return null;
    }

    @Override
    public Page<CarWashServiceFullDto> findAll(Pageable pageable, CarWashServiceFilter carWashServiceFilter) {
        return null;
    }

    @Override
    public CarWashServiceFullDto save(CarWashServiceCreateDto carWashServiceCreateDto) {
        return null;
    }

    @Override
    public CarWashServiceFullDto update(CarWashServiceUpdateDto carWashServiceUpdateDto, Long id) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }
}
