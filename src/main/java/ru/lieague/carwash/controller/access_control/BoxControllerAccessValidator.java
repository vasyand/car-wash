package ru.lieague.carwash.controller.access_control;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.user.UserSecurity;
import ru.lieague.carwash.service.BoxService;

import static ru.lieague.carwash.controller.access_control.CurrentUserUtil.getCurrentUser;
import static ru.lieague.carwash.model.UserRole.ADMIN;
import static ru.lieague.carwash.model.UserRole.OPERATOR;

@Component
@RequiredArgsConstructor
public class BoxControllerAccessValidator {

    private final BoxService boxService;

    public boolean canTheCurrentUserViewThisBox(Long id) {
        BoxFullDto box = boxService.findById(id);
        Long userId = box.getOperatorId();
        UserSecurity user = getCurrentUser();
        return user.getRole() == ADMIN
                || (user.getRole() == OPERATOR && user.getId().equals(userId));
    }
}
