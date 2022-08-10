package ru.lieague.carwash.controller.access_control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.lieague.carwash.config.security.UserDetailsImpl;
import ru.lieague.carwash.model.dto.user.UserSecurity;

class CurrentUserUtil {
    public static UserSecurity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return principal.getUser();
    }
}
