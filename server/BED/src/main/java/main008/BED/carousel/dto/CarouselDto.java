package main008.BED.carousel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.core.io.ByteArrayResource;

public class CarouselDto {

    @Getter
    @AllArgsConstructor
    public static class ResponseDto {

        private String title;
        private String subTitle;
        private String url;
    }


}
