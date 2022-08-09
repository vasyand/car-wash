package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import ru.lieague.carwash.validation.annotation.EmailIsNotExistInDataBase;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class UserCreateDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String middleName;

    @NotBlank
    private String lastName;

    @Email
    @EmailIsNotExistInDataBase
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String mobile;
}
