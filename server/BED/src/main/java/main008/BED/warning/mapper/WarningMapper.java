package main008.BED.warning.mapper;

import main008.BED.warning.dto.WarningDto;
import main008.BED.warning.entity.Warning;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WarningMapper {

    Warning postDtoToEntity(WarningDto.Post post);
}
