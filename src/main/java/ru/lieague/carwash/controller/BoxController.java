package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.BoxCreateDto;
import ru.lieague.carwash.model.dto.BoxFullDto;
import ru.lieague.carwash.model.dto.BoxUpdateDto;
import ru.lieague.carwash.model.filter.BoxFilter;
import ru.lieague.carwash.service.BoxService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/boxes")
public class BoxController {
    private final BoxService boxService;

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public BoxFullDto findById(@PathVariable Long id) {
        return boxService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<BoxFullDto> findAll(Pageable pageable, BoxFilter boxFilter) {
        return boxService.findAll(pageable, boxFilter);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public BoxFullDto create(@RequestBody BoxCreateDto boxCreateDto) {
        return boxService.save(boxCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public BoxFullDto update(@RequestBody BoxUpdateDto boxUpdateDto,
                             @PathVariable Long id) {
        return boxService.update(boxUpdateDto, id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return boxService.delete(id);
    }
}
