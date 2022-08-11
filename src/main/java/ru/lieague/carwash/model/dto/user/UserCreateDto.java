package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import ru.lieague.carwash.validation.annotation.EmailIsNotExistInDataBase;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UserCreateDto {

    @NotNull
    private String firstName;

    @NotNull
    private String middleName;

    @NotNull
    private String lastName;

    @NotNull
    @Email
    @EmailIsNotExistInDataBase
    private String email;

    @NotNull
    private String password;
}
