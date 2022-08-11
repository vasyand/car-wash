package ru.lieague.carwash.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lieague.carwash.exception.EntityNotFoundException;
import ru.lieague.carwash.mapper.UserMapper;
import ru.lieague.carwash.model.dto.user.*;
import ru.lieague.carwash.model.entity.User;
import ru.lieague.carwash.model.filter.UserFilter;
import ru.lieague.carwash.repository.UserRepository;
import ru.lieague.carwash.service.UserService;
import ru.lieague.carwash.specification.UserSpecification;

import javax.transaction.Transactional;

import java.util.Optional;

import static java.lang.String.format;
import static ru.lieague.carwash.model.UserRole.USER;
import static ru.lieague.carwash.specification.UserSpecification.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserGetDto findById(Long id) {
        return userMapper.userToUserGetDto(findUserByIdOrThrowException(id));
    }

    @Override
    public Optional<UserSecurity> findByEmail(String email) {
        return userRepository.findOne(UserSpecification.findByEmail(email))
                .map(userMapper::userToUserSecurityDto);
    }

    @Override
    public Page<UserGetDto> findAll(Pageable pageable, UserFilter userFilter) {
        return userRepository.findAll(generateSpecification(userFilter), pageable)
                .map(userMapper::userToUserGetDto);
    }

    @Override
    public UserGetDto save(UserCreateDto userCreateDto) {
        User user = userMapper.userCreateDtoToUser(userCreateDto);
        user.setRole(USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        User user = findUserByIdOrThrowException(id);
        user.setEnabled(false);
        return user.getId();
    }

    @Override
    public UserGetDto changeRole(UserChangeRoleDto userChangeRoleDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        user.setRole(userChangeRoleDto.getRole());
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    @Override
    public UserGetDto setDiscount(UserSetDiscountDto userSetDiscountDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        user.setDiscount(userSetDiscountDto.getDiscount());
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserGetDto update(UserUpdateDto userUpdateDto, Long id) {
        User user = findUserByIdOrThrowException(id);
        userMapper.userUpdateDtoMergeWithUser(userUpdateDto, user);
        return userMapper.userToUserGetDto(userRepository.save(user));
    }

    private User findUserByIdOrThrowException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(format("Пользователя с id %s не существует", id)));
    }
}
