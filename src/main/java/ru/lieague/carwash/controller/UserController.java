package ru.lieague.carwash.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.lieague.carwash.model.dto.user.*;
import ru.lieague.carwash.model.filter.UserFilter;
import ru.lieague.carwash.service.UserService;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @ResponseStatus(OK)
    @GetMapping("/{id}")
    @PreAuthorize("@userControllerAccessValidator.canTheCurrentUserViewThisUser(#id)")
    public UserGetDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<UserGetDto> findAll(Pageable pageable, UserFilter userFilter) {
        return userService.findAll(pageable, userFilter);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public UserGetDto create(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    @PreAuthorize("@userControllerAccessValidator.canTheCurrentUserViewThisUser(#id)")
    public UserGetDto update(@RequestBody UserUpdateDto userUpdateDto,
                             @PathVariable Long id) {
        return userService.update(userUpdateDto, id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}/role")
    public UserGetDto changeUserRole(@RequestBody UserChangeRoleDto userChangeRoleDto,
                                      @PathVariable Long id) {
        return userService.changeRole(userChangeRoleDto, id);
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}/discount")
    public UserGetDto setDiscount(@RequestBody @Valid UserSetDiscountDto userSetDiscountDto,
                                   @PathVariable Long id) {
        return userService.setDiscount(userSetDiscountDto, id);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{id}")
    @PreAuthorize("@userControllerAccessValidator.canTheCurrentUserViewThisUser(#id)")
    public Long delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
