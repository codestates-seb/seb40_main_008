package main008.BED.warning.mapper;

import main008.BED.warning.dto.WarningDto;
import main008.BED.warning.entity.Warning;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface WarningMapper {

    Warning postDtoToEntity(WarningDto.Post post);

    default List<WarningDto.Response> listEntityToListResponseDto(List<Warning> warningList) {
        List<WarningDto.Response> responseList = warningList.stream()
                .map(warning -> this.entityToResponseDto(warning))
                .collect(Collectors.toList());
        return responseList;
    }

    default WarningDto.Response entityToResponseDto(Warning warning) {
        return new WarningDto.Response()
                .builder()
                .uploadClassId(warning.getUploadClass().getUploadClassId())
                .warningId(warning.getWarningId())
                .reason(warning.getReason())
                .createdAt(warning.getCreatedAt())
                .usersId(warning.getUsers().getUsersId())
                .title(warning.getUploadClass().getTitle())
                .build();
    }
}
