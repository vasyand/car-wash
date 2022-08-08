package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.user.*;
import ru.lieague.carwash.model.filter.UserFilter;

public interface UserService {
    UserFullDto findById(Long id);
    UserFullDto save(UserCreateDto userCreateDto);
    UserFullDto update(UserUpdateDto userUpdateDto, Long id);
    UserFullDto changeRole(UserChangeRoleDto userChangeRoleDto, Long id);
    UserFullDto setDiscount(UserSetDiscountDto userSetDiscountDto, Long id);
    Long delete(Long id);
    Page<UserFullDto> findAll(Pageable pageable, UserFilter userFilter);
}
