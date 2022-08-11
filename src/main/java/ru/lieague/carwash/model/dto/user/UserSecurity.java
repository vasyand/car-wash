package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.lieague.carwash.model.UserRole;

@Getter
@Setter
public class UserSecurity {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private Double discount;
    private UserRole role;
    private boolean isEnabled;
}
