package main008.BED.warning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

public class WarningDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String reason;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {

        private Long usersId;
        private Long warningId;
        private Long uploadClassId;
        private String title;
        private String reason;
        private ZonedDateTime createdAt;

    }

    @Getter
    @AllArgsConstructor
    public static class ResponseList {
        List<Response> warningList;
    }


}
