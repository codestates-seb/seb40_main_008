package main008.BED.users.dto;

import lombok.*;

public class UsersDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String email;
        private String userName;
        private String profileImage;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Patch {

        private Long usersId;
        private String email;
        private String userName;
        private String profileImage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserResponseToHome {

        private Long usersId;
        private String userName;
        private String profileImage;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserResponseToMyPage {

        private Long usersId;
        private String userName;
        private String email;
        private String profileImage;
        private int coin;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private Long usersId;
        private String email;
        private String userName;
        private String profileImage;
    }
}
