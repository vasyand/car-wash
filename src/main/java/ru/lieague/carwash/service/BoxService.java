package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.BoxCreateDto;
import ru.lieague.carwash.model.dto.BoxFullDto;
import ru.lieague.carwash.model.dto.BoxUpdateDto;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.filter.BoxFilter;

import java.time.LocalDateTime;

public interface BoxService {
    BoxFullDto findById(Long id);
    Page<BoxFullDto> findAll(Pageable pageable, BoxFilter boxFilter);
    BoxFullDto findTheBestBoxForCarWashServiceAtTime(Integer standardWashDuration, LocalDateTime time);

    BoxFullDto save(BoxCreateDto boxCreateDto);

    BoxFullDto update(BoxUpdateDto boxUpdateDto, Long id);

    Long delete(Long id);

}
