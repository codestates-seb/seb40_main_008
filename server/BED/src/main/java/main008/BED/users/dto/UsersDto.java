package main008.BED.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UsersDto {

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
    public static class Response {

        private Long id;
        private String username;
        private String profileImage;
    }
}
