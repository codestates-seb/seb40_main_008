package main008.BED.mainPage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import main008.BED.contents.dto.ContentsDto;

import java.util.List;

public class MainPageDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class NotLoginResponse {

        private List<ContentsDto.Response> contentsList;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LoginResponse {

        private Long usersId;
        private List<ContentsDto.Response> contentsList;
    }
}
