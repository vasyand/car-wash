package ru.lieague.carwash.model.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
}
