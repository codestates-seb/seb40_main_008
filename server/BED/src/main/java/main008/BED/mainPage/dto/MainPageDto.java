package main008.BED.mainPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;

import java.util.List;

public class MainPageDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class NotLoginResponse {

//        private Contents.Categories[] categories;
        private List<ContentsDto.Response> contentsList;
    }
}
