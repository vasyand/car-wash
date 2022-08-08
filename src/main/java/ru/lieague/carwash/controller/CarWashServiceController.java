package ru.lieague.carwash.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.CarWashServiceCreateDto;
import ru.lieague.carwash.model.dto.CarWashServiceFullDto;
import ru.lieague.carwash.model.dto.CarWashServiceUpdateDto;
import ru.lieague.carwash.model.filter.CarWashServiceFilter;
import ru.lieague.carwash.service.CarWashServiceService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car-wash-services")
public class CarWashServiceController {
    private final CarWashServiceService carWashServiceService;

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public CarWashServiceFullDto findById(@PathVariable Long id) {
        return carWashServiceService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<CarWashServiceFullDto> findAll(Pageable pageable,
                                               CarWashServiceFilter carWashServiceFilter) {
        return carWashServiceService.findAll(pageable, carWashServiceFilter);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public CarWashServiceFullDto create(@RequestBody CarWashServiceCreateDto carWashServiceCreateDto) {
        return carWashServiceService.save(carWashServiceCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public CarWashServiceFullDto update(@RequestBody CarWashServiceUpdateDto carWashServiceUpdateDto,
                             @PathVariable Long id) {
        return carWashServiceService.update(carWashServiceUpdateDto, id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return carWashServiceService.delete(id);
    }
}
