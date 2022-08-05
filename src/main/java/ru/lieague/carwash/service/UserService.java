package ru.lieague.carwash.service;

import ru.lieague.carwash.model.entity.User;

public interface UserService {
    User findById(Long id);
}
