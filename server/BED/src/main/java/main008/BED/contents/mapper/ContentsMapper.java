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
                        .likesCount(content.getLikesCount())
                        .categories(content.getCategories())
                        .users(usersMapper.usersToResponse(content.getUsers()))
                        .build())
                .collect(Collectors.toList());
    }

    default List<ContentsDto.ResponseForCategories> contentsToCategoriesResponses(List<Contents> contents, UsersMapper usersMapper) {

        return contents.stream()
                .map(content -> ContentsDto.ResponseForCategories.builder()
                        .contentsId(content.getContentsId())
                        .title(content.getTitle())
                        .thumbnail(content.getThumbnail())
                        .categories(content.getCategories())
//                        .likesCount(content.getLikesCount())
                        .users(usersMapper.usersToResponse(content.getUsers()))
                        .build())
                .collect(Collectors.toList());
    }

    default ContentsDto.CategoryListResponse toCategoryList(List<ContentsDto.ResponseForCategories> categories) {

        return ContentsDto.CategoryListResponse.builder()
                .contentsList(categories)
                .build();
    }


    default ContentsDto.ResponseForTitleSearch contentsPageToResponse(Contents contents) {
            if ( contents == null ) {
                return null;
            }

            String title = null;
            String thumbnail = null;
            Contents.Categories categories = null;
            String tutorName = null;

            title = contents.getTitle();
            thumbnail = contents.getThumbnail();
            categories = contents.getCategories();
            tutorName = contents.getUsers().getUserName();


            ContentsDto.ResponseForTitleSearch responseForTitleSearch = new ContentsDto.ResponseForTitleSearch( title, thumbnail, categories, tutorName );

            return responseForTitleSearch;
    }

    default List<ContentsDto.ResponseForTitleSearch> contentsPageToResponses(List<Contents> contents) {
        return contents.stream()
                .map(content -> contentsPageToResponse(content))
                .collect(Collectors.toList());
    }

}
