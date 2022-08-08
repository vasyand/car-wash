package ru.lieague.carwash.mapper;

import org.mapstruct.MappingTarget;
import ru.lieague.carwash.model.dto.BoxCreateDto;
import ru.lieague.carwash.model.dto.BoxFullDto;
import ru.lieague.carwash.model.dto.BoxUpdateDto;
import ru.lieague.carwash.model.entity.Box;

public interface BoxMapper {
    BoxFullDto boxToBoxFullDto(Box box);

    Box boxCreateDtoToBox(BoxCreateDto boxCreateDto);

    Box boxUpdateDtoMergeWithBox(BoxUpdateDto boxUpdateDto, @MappingTarget Box box);
}
