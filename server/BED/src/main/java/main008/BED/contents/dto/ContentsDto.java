package main008.BED.contents.dto;

import lombok.*;
import main008.BED.bookmark.dto.BookmarkDto;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.contents.entity.Contents;
import main008.BED.docs.dto.DocsDto;
import main008.BED.review.dto.ReviewDto;
import main008.BED.users.dto.UsersDto;

import java.util.List;

public class ContentsDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String title;
        private Contents.Categories categories;
        private String details;
        private String tutorDetail;
        private String thumbnail;
        private String fileKey;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private String title;
        private Contents.Categories categories;
        private String details;
        private String tutorDetail;
        private String thumbnail;
        private String fileKey;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private Long contentsId;
        private String title;
        private String thumbnail;
        private int likesCount;
        private Contents.Categories categories;
        private UsersDto.UserResponseToHome users;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseInContent {

        private Long contentsId;
        private String title;
        private String thumbnail;
        private int likesCount;
        private Contents.Categories categories;
        private double grade;
        private int price;
        private String role;
        private boolean liked;
        private boolean wished;
        private String tutorName;
        private String details;
        private String tutorDetail;
//        private List<ChapterDto.ResponseDto> curriculumInfo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseInPreview {

        private Long contentsId;
        private String title;
        private String thumbnail;
        private Contents.Categories categories;
        private int starRateAvg;
        private int price;
        private String details;
        private String tutorDetail;
        private List<ChapterDto.ResponseDto> curriculumInfo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseForStream {

        private UsersDto.Response userInfo;
        private String title;
        private String video;
        private DocsDto.Response docsInfo;
        private List<ReviewDto.Response> reviewInfo; // 리뷰 작성자 이름, 작성 시간, 내용 -> 리스트
        private List<BookmarkDto.Response> bookmarkInfo; // 북마크 시간, 메모 내용 -> 리스트
        private List<ChapterDto.ResponseDtoWithoutThumbnail> curriculumInfo;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseForTitleSearch {

        private String title;
        private String thumbnail;
        private Contents.Categories categories;
        private String tutorName;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseForCategories {

        private Long contentsId;
        private String title;
        private String thumbnail;
//        private int likesCount;
        private Contents.Categories categories;
        private UsersDto.UserResponseToHome users;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CategoryListResponse {

        private List<ResponseForCategories> contentsList;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContentsId {

        private Long contentsId;
        private List<ChapterDto.ResponseListDto> chapterList;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MUCResponse {

        private Long contentsId;
        private String title;
        private String thumbnail;
        private int likesCount;
        private Contents.Categories categories;
        private UsersDto.UserResponseToHome users;
        private Integer price;
        private String details;
        private String tutorDetail;
    }
}
