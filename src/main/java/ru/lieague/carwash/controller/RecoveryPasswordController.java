package ru.lieague.carwash.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.PasswordRecoveryDto;
import ru.lieague.carwash.service.UserService;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recovery-password")
public class RecoveryPasswordController {
    private final UserService userService;

    @ResponseStatus(OK)
    @PutMapping
    public String recoveryPassword(@RequestParam String email,
                                   @RequestBody PasswordRecoveryDto passwordRecoveryDto) {
        return userService.setNewPassword(passwordRecoveryDto, email);
    }
}
