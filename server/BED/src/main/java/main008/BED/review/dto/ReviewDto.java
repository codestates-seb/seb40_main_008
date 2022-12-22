package main008.BED.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

public class ReviewDto {
    @Getter
    @AllArgsConstructor
    public static class Post {

        private String comments;
        private int starRate;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private String comments;
        private int starRate;

    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long usersId;
        private String userName;
        private String profileImage;
        private Long reviewId;
        private int starRate;
        private String comments;
        private ZonedDateTime createdAt;
        private ZonedDateTime modifiedAt;
    }

}
