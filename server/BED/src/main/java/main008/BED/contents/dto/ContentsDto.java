package main008.BED.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.contents.entity.Contents;
import main008.BED.docs.entity.Docs;
import main008.BED.likes.dto.LikesDto;
import main008.BED.review.entity.Review;
import main008.BED.users.dto.UsersDto;

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
        private String details;
        private String tutorDetail;
        private ChapterDto.CurriculumInContent curriculumInContent;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ResponseForStream {

        private String userName;
        private String title;
        private String video;
        private Docs docs;
        private Review review; // 리뷰 작성자 이름, 작성 시간, 내용 -> 리스트
        private Bookmark bookmark; // 북마크 시간, 메모 내용 -> 리스트
        private ChapterDto.CurriculumInStream curriculumInStream;
    }


}
