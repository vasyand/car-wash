package ru.lieague.carwash.model.dto.user;

import lombok.Getter;
import ru.lieague.carwash.model.UserRole;

import javax.validation.constraints.NotNull;

@Getter
public class UserChangeRoleDto {
    @NotNull
    private UserRole role;
}
