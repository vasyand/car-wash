package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import ru.lieague.carwash.model.UserRole;

@Getter
public class UserChangeRoleDto {
    private UserRole role;
}
