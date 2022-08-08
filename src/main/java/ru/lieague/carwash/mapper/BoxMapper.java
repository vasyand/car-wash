package ru.lieague.carwash.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import ru.lieague.carwash.model.dto.box.BoxCreateDto;
import ru.lieague.carwash.model.dto.box.BoxFullDto;
import ru.lieague.carwash.model.dto.box.BoxUpdateDto;
import ru.lieague.carwash.model.entity.Box;
import ru.lieague.carwash.model.entity.User;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING)
public interface BoxMapper {

    @Mapping(target = "operatorId", source = "box.operator.id")
    BoxFullDto boxToBoxFullDto(Box box);

    @Mapping(target = "operator", source = "operatorId", qualifiedByName = "createUserWithId")
    Box boxCreateDtoToBox(BoxCreateDto boxCreateDto);

    @Mapping(target = "startWorking", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "endWorking", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "coefficient", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "name", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "operator", source = "operatorId",
            qualifiedByName = "createUserWithId", nullValuePropertyMappingStrategy = IGNORE)
    Box boxUpdateDtoMergeWithBox(BoxUpdateDto boxUpdateDto, @MappingTarget Box box);

    @Named("createUserWithId")
    static User createUserWithId(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
