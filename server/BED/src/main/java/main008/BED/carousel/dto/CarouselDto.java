package main008.BED.carousel.dto;

import lombok.*;

import java.util.List;

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
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto {

        private Long carouselId;
        private String imageUrl;
        private String title;
        private String subTitle;
        private String redirectUrl;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResponseDto {
        private List<ResponseDto> carouselInfo;
    }
}
