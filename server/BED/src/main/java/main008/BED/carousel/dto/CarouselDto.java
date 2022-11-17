package main008.BED.carousel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CarouselDto {

    @Getter
    @AllArgsConstructor
    public static class ResponseDto {

        private String imageUrl;
        private String title;
        private String subTitle;
        private String redirectUrl;
    }

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String name;
        private String imageUrl;
    }
}
