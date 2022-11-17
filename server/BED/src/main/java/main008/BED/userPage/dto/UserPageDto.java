package main008.BED.userPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.coinCharge.entity.CoinCharge;
import main008.BED.contents.entity.Contents;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.users.dto.UsersDto;

public class UserPageDto {

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

        private UsersDto.UserResponseToMyPage users;
    }
}
