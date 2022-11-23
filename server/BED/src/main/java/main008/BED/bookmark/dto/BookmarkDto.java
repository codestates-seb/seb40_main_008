package main008.BED.bookmark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

public class BookmarkDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String memo;
//        private String timeline; // advanced

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Patch {

        private String memo;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {

        private Long usersId;
        private Long bookmarkId;
        private String memo;
        private ZonedDateTime createdAt;
        private ZonedDateTime modifiedAt;

    }

}
