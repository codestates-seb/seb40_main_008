package main008.BED.review.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

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
    }
}
