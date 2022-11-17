package main008.BED.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.myClass.dto.MyClassDto;
import main008.BED.users.dto.UsersDto;

public class WishDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {
        private Boolean wished;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Boolean wished;
        private ContentsDto.Response contents;
    }
}
