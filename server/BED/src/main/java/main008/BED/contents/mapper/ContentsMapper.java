package main008.BED.contents.mapper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.users.mapper.UsersMapper;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContentsMapper {

    Contents postToContents(ContentsDto.Post post);

    ContentsDto.Response contentsToResponse(Contents contents);

    default List<ContentsDto.Response> contentsToResponses(List<Contents> contents, UsersMapper usersMapper) {

        return contents.stream()
                .map(content -> ContentsDto.Response.builder()
                        .contentsId(content.getContentsId())
                        .title(content.getTitle())
                        .thumbnail(content.getThumbnail())
                        .categories(content.getCategories())
                        .users(usersMapper.usersToResponse(content.getUsers()))
                        .build())
                .collect(Collectors.toList());

//        return List.of(
//                new ContentsDto.Response(
//                        3L,
//                        "title3",
//                        "title3 thumbnail",
//                        Contents.Categories.PROGRAMMING,
//                        new UsersDto.UserResponseToHome(
//                                1L,
//                                "testUser1",
//                                "testUser1 profileImage")),
//                new ContentsDto.Response(
//                        2L,
//                        "title2",
//                        "title2 thumbnail",
//                        Contents.Categories.BAKING_DESSERT,
//                        new UsersDto.UserResponseToHome(
//                                2L,
//                                "testUser2",
//                                "testUser2 profileImage")),
//                new ContentsDto.Response(
//                        1L,
//                        "title1",
//                        "title1 thumbnail",
//                        Contents.Categories.CRAFTS,
//                        new UsersDto.UserResponseToHome(
//                                3L,
//                                "testUser3",
//                                "testUser3 profileImage")));
    }
}
