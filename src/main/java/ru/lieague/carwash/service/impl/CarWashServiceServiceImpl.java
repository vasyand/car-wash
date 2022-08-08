package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.mapper.CarWashServiceMapper;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.car_wash_service.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;
import ru.lieague.carwash.repository.CarWashServiceRepository;
import ru.lieague.carwash.service.CarWashServiceService;

import javax.transaction.Transactional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CarWashServiceServiceImpl implements CarWashServiceService {
    private final CarWashServiceRepository carWashServiceRepository;
    private final CarWashServiceMapper carWashServiceMapper;

    @Override
    public CarWashServiceFullDto findById(Long id) {
        return carWashServiceMapper
                .carWashServiceToCarWashServiceFullDto(findCarWashServiceByIdOrThrowException(id));
    }

    @Override
    public Page<CarWashServiceFullDto> findAll(Pageable pageable, CarWashServiceFilter carWashServiceFilter) {
        return carWashServiceRepository.findAll(pageable)
                .map(carWashServiceMapper::carWashServiceToCarWashServiceFullDto);
    }

    @Override
    public CarWashServiceFullDto save(CarWashServiceCreateDto carWashServiceCreateDto) {
        return carWashServiceMapper.carWashServiceToCarWashServiceFullDto(
                carWashServiceRepository.save(
                        carWashServiceMapper.carWashServiceCreateDtoToCarWashService(carWashServiceCreateDto)
                )
        );
    }

    @Override
    @Transactional
    public CarWashServiceFullDto update(CarWashServiceUpdateDto carWashServiceUpdateDto, Long id) {
        CarWashService carWashService = findCarWashServiceByIdOrThrowException(id);
        carWashServiceMapper.carWashServiceUpdateMergeWithCarWashService(
                carWashServiceUpdateDto,
                carWashService
        );
        return carWashServiceMapper.carWashServiceToCarWashServiceFullDto(
                carWashServiceRepository.save(carWashService)
        );
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        CarWashService carWashService = findCarWashServiceByIdOrThrowException(id);
        carWashServiceRepository.delete(carWashService);
        return carWashService.getId();
    }

    private CarWashService findCarWashServiceByIdOrThrowException(Long id) {
        return carWashServiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Услуги с id %s не существует", id)));
    }
}
