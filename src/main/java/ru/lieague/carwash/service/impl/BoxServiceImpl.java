package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.exception.FreeBoxNotFoundException;
import ru.lieague.carwash.mapper.BoxMapper;
import ru.lieague.carwash.model.dto.box.BoxCreateDto;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.box.BoxUpdateDto;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.filter.BoxFilter;
import ru.lieague.carwash.repository.BoxRepository;
import ru.lieague.carwash.repository.UserRepository;
import ru.lieague.carwash.service.BoxService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static java.lang.String.format;
import static ru.lieague.carwash.specification.BoxSpecification.generateSpecification;

@Service
@RequiredArgsConstructor
public class BoxServiceImpl implements BoxService {
    private final BoxRepository boxRepository;
    private final UserRepository userRepository;
    private final BoxMapper boxMapper;

    @Override
    public BoxFullDto findById(Long id) {
        return boxMapper.boxToBoxFullDto(findBoxByIdOrThrowException(id));
    }

    @Override
    public Page<BoxFullDto> findAll(Pageable pageable, BoxFilter boxFilter) {
        return boxRepository.findAll(generateSpecification(boxFilter), pageable)
                .map(boxMapper::boxToBoxFullDto);
    }

    @Override
    public BoxFullDto findTheBestBoxForCarWashServiceAtTime(Integer standardWashDuration, LocalDateTime time) {
        return boxMapper.boxToBoxFullDto(
                boxRepository.getBestBoxAtThisTime(standardWashDuration, time)
                        .orElseThrow(() -> new FreeBoxNotFoundException("Свободный бокс на это время отсутствует"))
        );
    }

    @Override
    public BoxFullDto save(BoxCreateDto boxCreateDto) {
        return boxMapper.boxToBoxFullDto(
                boxRepository.save(boxMapper.boxCreateDtoToBox(boxCreateDto))
        );
    }

    @Override
    @Transactional
    public BoxFullDto update(BoxUpdateDto boxUpdateDto, Long id) {
        Box box = findBoxByIdOrThrowException(id);
        boxMapper.boxUpdateDtoMergeWithBox(boxUpdateDto, box);
        userRepository.findById(box.getOperator().getId());
        return boxMapper.boxToBoxFullDto(boxRepository.save(box));
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        Box box = findBoxByIdOrThrowException(id);
        box.setWorked(false);
        return box.getId();
    }

    private Box findBoxByIdOrThrowException(Long id) {
        return boxRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Бокса с id %s не существует", id)));
    }

}
