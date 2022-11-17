package main008.BED.carousel.dto;

import lombok.*;

public class CarouselDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String name;
        private String keys;
        private String imageUrl;
        private String title;
        private String subTitle;
        private String redirectUrl;
    }

    @Getter
    @NoArgsConstructor
    @Setter
    @AllArgsConstructor
    @Builder
    public static class ResponseDto {

        private Long carouselId;
        private String imageUrl;
        private String title;
        private String subTitle;
        private String redirectUrl;
    }
}
