package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.user.*;
import ru.lieague.carwash.model.filter.UserFilter;

import java.util.Optional;

public interface UserService {
    UserGetDto findById(Long id);
    Optional<UserSecurity> findByEmail(String email);
    UserGetDto save(UserCreateDto userCreateDto);
    UserGetDto update(UserUpdateDto userUpdateDto, Long id);
    UserGetDto changeRole(UserChangeRoleDto userChangeRoleDto, Long id);
    UserGetDto setDiscount(UserSetDiscountDto userSetDiscountDto, Long id);
       Long delete(Long id);
    Page<UserGetDto> findAll(Pageable pageable, UserFilter userFilter);

}
