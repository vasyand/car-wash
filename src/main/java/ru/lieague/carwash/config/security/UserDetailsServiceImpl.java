package ru.lieague.carwash.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.service.UserService;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserDetailsImpl(userService.findByEmail(email)
                .orElseThrow(
                        () -> new EntityNotFoundException(format("Пользователя с почтой %s не существует", email)))
        );
    }
}
