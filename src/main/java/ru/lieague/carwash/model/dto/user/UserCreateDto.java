package ru.lieague.carwash.model.dto.user;

import lombok.Getter;

@Getter
public class UserCreateDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private String mobile;
}
