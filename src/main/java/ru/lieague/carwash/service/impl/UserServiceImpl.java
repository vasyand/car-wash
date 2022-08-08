package ru.lieague.carwash.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.model.dto.UserCreateDto;
import ru.lieague.carwash.model.dto.UserFullDto;
import ru.lieague.carwash.model.dto.UserUpdateDto;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.filter.UserFilter;
import ru.lieague.carwash.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserFullDto findById(Long id) {
        return null;
    }

    @Override
    public UserFullDto save(UserCreateDto userCreateDto) {
        return null;
    }

    @Override
    public Long delete(Long id) {
        return null;
    }

    @Override
    public UserFullDto update(UserUpdateDto userUpdateDto, Long id) {
        return null;
    }

    @Override
    public Page<UserFullDto> findAll(Pageable pageable, UserFilter userFilter) {
        return null;
    }
}
