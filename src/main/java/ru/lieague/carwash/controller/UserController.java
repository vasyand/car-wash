package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.UserCreateDto;
import ru.lieague.carwash.model.dto.UserFullDto;
import ru.lieague.carwash.model.dto.UserUpdateDto;
import ru.lieague.carwash.model.filter.UserFilter;
import ru.lieague.carwash.service.UserService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    public UserFullDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<UserFullDto> findAll(Pageable pageable, UserFilter userFilter) {
        return userService.findAll(pageable, userFilter);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public UserFullDto create(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public UserFullDto update(@RequestBody UserUpdateDto userUpdateDto,
                             @PathVariable Long id) {
        return userService.update(userUpdateDto, id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
