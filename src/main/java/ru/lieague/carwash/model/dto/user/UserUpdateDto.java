package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String mobile;
}
