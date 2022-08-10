package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.user.UserCreateDto;
import ru.lieague.carwash.model.dto.user.UserGetDto;
import ru.lieague.carwash.service.UserService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final UserService userService;

    @ResponseStatus(CREATED)
    @PostMapping
    public UserGetDto register(@RequestBody @Valid UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }
}
