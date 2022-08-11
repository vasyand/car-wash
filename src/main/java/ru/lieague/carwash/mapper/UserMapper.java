package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.lieague.carwash.model.dto.user.UserCreateDto;
import ru.lieague.carwash.model.dto.user.UserGetDto;
import ru.lieague.carwash.model.dto.user.UserSecurity;
import ru.lieague.carwash.model.dto.user.UserUpdateDto;
import ru.lieague.carwash.model.entity.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING, uses = {
        BookingMapper.class,
        PaymentStatisticMapper.class
})
public interface UserMapper {
    UserGetDto userToUserGetDto(User user);

    UserSecurity userToUserSecurityDto(User user);

    User userCreateDtoToUser(UserCreateDto userCreateDto);

    @Mapping(target = "firstName", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "middleName", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "lastName", nullValuePropertyMappingStrategy = IGNORE)
    User userUpdateDtoMergeWithUser(UserUpdateDto userUpdateDto,
                                    @MappingTarget User user);
}
