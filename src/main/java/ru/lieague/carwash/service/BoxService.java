package ru.lieague.carwash.service;

import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.CarWashService;
import ru.lieague.carwash.model.filter.BoxFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BoxService {
    Box findById(Long id);
    Box findAll(Pageable pageable, BoxFilter boxFilter);
    Box findTheBestBoxForCarWashServiceAtTime(CarWashService carWashService, LocalDateTime time);

}
