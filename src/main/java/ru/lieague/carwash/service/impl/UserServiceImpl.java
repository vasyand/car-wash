package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.mapper.UserMapper;
import ru.lieague.carwash.model.dto.user.*;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.filter.UserFilter;
import ru.lieague.carwash.repository.UserRepository;
import ru.lieague.carwash.service.UserService;

import javax.transaction.Transactional;

import static java.lang.String.format;
import static ru.lieague.carwash.model.UserRole.USER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserFullDto findById(Long id) {
        return userMapper.userToUserFullDto(findUserByIdOrThrowException(id));
    }

    @Override
    public Page<UserFullDto> findAll(Pageable pageable, UserFilter userFilter) {
        return userRepository.findAll(pageable)
                .map(userMapper::userToUserFullDto);
    }

    @Override
    public UserFullDto save(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        user.setRole(USER);
        //TODO encode password
        return userMapper.userToUserFullDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        User user = findUserByIdOrThrowException(id);
        userRepository.delete(user);
        return user.getId();
    }

    @Override
    public UserFullDto changeRole(UserChangeRoleDto userChangeRoleDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        user.setRole(userChangeRoleDto.getRole());
        return userMapper.userToUserFullDto(userRepository.save(user));
    }

    @Override
    public UserFullDto setDiscount(UserSetDiscountDto userSetDiscountDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        user.setDiscount(userSetDiscountDto.getDiscount());
        return userMapper.userToUserFullDto(userRepository.save(user));
    }

    @Override
    public UserFullDto update(UserUpdateDto userUpdateDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        userMapper.userUpdateDtoMergeWithUser(userUpdateDto, user);
        return userMapper.userToUserFullDto(userRepository.save(user));
    }

    private User findUserByIdOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Пользователя с id %s не существует", id)));
    }
}
