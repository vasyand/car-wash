package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.filter.BoxFilter;
import ru.lieague.carwash.repository.BoxRepository;
import ru.lieague.carwash.service.BoxService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;

    @Override
    public Box findById(Long id) {
        return null;
    }

    @Override
    public Box findAll(Pageable pageable, BoxFilter boxFilter) {
        return null;
    }

    @Override
    public Box findTheBestBoxForCarWashServiceAtTime(CarWashService carWashService, LocalDateTime time) {
        return boxRepository.getBestBoxAtThisTime(carWashService.getDuration(), time).get();
    }


}
