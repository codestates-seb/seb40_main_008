package main008.BED.userPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.users.dto.UsersDto;

public class UserPageDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Response {

        private UsersDto.UserResponseToMyPage users;
    }
}
