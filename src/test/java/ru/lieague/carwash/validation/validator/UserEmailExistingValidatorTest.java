package ru.lieague.carwash.validation.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.lieague.carwash.model.dto.user.UserSecurity;
import ru.lieague.carwash.service.UserService;

import javax.validation.ConstraintValidatorContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserEmailExistingValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserEmailExistingValidator validator;

    @Test
    @DisplayName("валидация существующего эмейла")
    void isValid_WhenEmailExist_ThenReturnTrue() {
        when(userService.findByEmail(anyString())).thenReturn(Optional.of(new UserSecurity()));
        boolean answer = validator.isValid(anyString(), context);
        assertTrue(answer);
    }

    @Test
    @DisplayName("валидация несуществующего эмейла")
    void isValid_WhenEmailNotExist_ThenReturnFalse() {
        when(userService.findByEmail(anyString())).thenReturn(Optional.empty());
        boolean answer = validator.isValid(anyString(), context);
        assertFalse(answer);
    }
}