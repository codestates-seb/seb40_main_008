package main008.BED.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.users.dto.UsersDto;

import java.time.ZonedDateTime;

public class LikesDetailDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        private Boolean liked;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Boolean liked;
        private UsersDto.Response users;
        private ZonedDateTime createdAt;
    }
}
