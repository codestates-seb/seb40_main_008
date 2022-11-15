package main008.BED.contents.mapper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.users.mapper.UsersMapper;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContentsMapper {

    default List<ContentsDto.Response> contentsToResponses(List<Contents> contents, UsersMapper usersMapper) {

        return contents.stream()
                .map(content -> ContentsDto.Response.builder()
                        .id(content.getId())
                        .title(content.getTitle())
                        .thumbnail(content.getClassProfile())
                        .users(usersMapper.usersToResponse(content.getUsers()))
                        .build())
                .collect(Collectors.toList());
    }
}
