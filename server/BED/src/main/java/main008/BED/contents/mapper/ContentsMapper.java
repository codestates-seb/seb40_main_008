package main008.BED.contents.mapper;

import main008.BED.bookmark.entity.Bookmark;
import main008.BED.bookmark.mapper.BookmarkMapper;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.service.ContentsService;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.review.entity.Review;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import org.mapstruct.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ContentsMapper {

    ContentsDto.Post reqToContentsPost(String title, Contents.Categories categories,
                                       String details, String tutorDetail, String thumbnail, String fileKey);

    Contents postToContents(ContentsDto.Post post);

    Contents patchToContents(ContentsDto.Patch patch);

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

    default ContentsDto.ResponseInContent contentToResponseInContent(Contents contents,
                                                                     HashMap<String, String> roleAndWish,
                                                                     ContentsService contentsService) {

        return new ContentsDto.ResponseInContent(
                contents.getContentsId(),
                contents.getTitle(),
                contents.getThumbnail(),
                contents.getLikesCount(),
                contents.getCategories(),
                contentsService.calculateAvgStar(contents.getContentsId()),
                contents.getPayment().getPrice(),
                roleAndWish.get("role"),
                Boolean.parseBoolean(roleAndWish.get("wished")),
                contents.getUsers().getUserName(),
                contents.getDetails(),
                contents.getTutorDetail()
        );
    }

    default ContentsDto.ResponseForStream contentsResponseForStream(Contents contents, UploadClass uploadClass,
                                                                    UsersMapper usersMapper, DocsMapper docsMapper,
                                                                    ReviewMapper reviewMapper, BookmarkMapper bookmarkMapper,
                                                                    List<Bookmark> bookmarkList,
                                                                    List<ChapterDto.ResponseDtoWithoutThumbnail> curriculumInfo) {

        Users tutor = contents.getUsers();
        String title = contents.getTitle();
        Docs docs = uploadClass.getDocs();
        String video = uploadClass.getVideo();
        List<Review> reviewList = uploadClass.getReviewList(); // 해당 강의 모든 리뷰 전송

        return new ContentsDto.ResponseForStream(
                usersMapper.usersToUserResponseDto(tutor),
                title,
                video,
                docsMapper.entityToResponseDto(docs),
                reviewMapper.listEntityToListResponseDto(reviewList),
                bookmarkMapper.listEntityToListResponseDto(bookmarkList),
                curriculumInfo
        );
    }

}
