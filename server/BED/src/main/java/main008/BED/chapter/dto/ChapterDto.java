package main008.BED.chapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ChapterDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

//        private Long contentsId;
        private String chapterOrder;
        private String title;
        private String thumbnail;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

    }

    @Getter
    @AllArgsConstructor
    public static class Response {

        private Long chapterId;
        private String chapterOrder;
        private String title;
        private String thumbnail;
    }
}
