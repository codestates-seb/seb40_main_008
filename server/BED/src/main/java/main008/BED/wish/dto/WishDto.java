package main008.BED.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.dto.ContentsDto;

public class WishDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        
        private String wished;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Boolean wished;
        private ContentsDto.Response contents;
    }
}
