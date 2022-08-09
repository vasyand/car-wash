package ru.lieague.carwash.controller.access_control;

import org.springframework.stereotype.Component;
import ru.lieague.carwash.model.dto.user.UserFullDto;

import static ru.lieague.carwash.controller.access_control.CurrentUserUtil.getCurrentUser;
import static ru.lieague.carwash.model.UserRole.ADMIN;
import static ru.lieague.carwash.model.UserRole.OPERATOR;

@Component
public class UserControllerAccessValidator {

    public boolean canTheCurrentUserViewThisUser(Long id) {
        UserFullDto user = getCurrentUser();
        return user.getRole() == ADMIN
                || user.getId().equals(id);
    }

    public boolean canTheCurrentUserSetDiscount() {
        UserFullDto user = getCurrentUser();
        return user.getRole() == ADMIN
                || user.getRole() == OPERATOR;
    }

}
