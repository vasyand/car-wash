package ru.lieague.carwash.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.lieague.carwash.model.dto.UserCreateDto;
import ru.lieague.carwash.model.dto.UserFullDto;
import ru.lieague.carwash.model.dto.UserUpdateDto;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.filter.UserFilter;

public interface UserService {
    UserFullDto findById(Long id);
    UserFullDto save(UserCreateDto userCreateDto);
    UserFullDto update(UserUpdateDto userUpdateDto, Long id);
    Long delete(Long id);
    Page<UserFullDto> findAll(Pageable pageable, UserFilter userFilter);
}
