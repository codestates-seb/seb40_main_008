package main008.BED.myClass.dto;

import lombok.*;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.wish.dto.WishDto;

import java.util.List;

public class MyClassDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TakingClassResponse {

        List<ContentsDto.Response> contents;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class WishClassResponse {

        List<WishDto.Response> wishes;
    }
}
