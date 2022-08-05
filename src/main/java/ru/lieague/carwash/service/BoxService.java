package ru.lieague.carwash.service;

import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.filter.BoxFilter;

import java.time.LocalDateTime;

public interface BoxService {
    Box findById(Long id);
    Box findAll(Pageable pageable, BoxFilter boxFilter);
    Box findTheBestBoxForCarWashServiceAtTime(Integer standardWashDuration, LocalDateTime time);

}
