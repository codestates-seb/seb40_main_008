package main008.BED.likes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.likes.entity.LikesDetail;

import java.util.List;

public class LikesDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private ContentsDto.Response contents;
        private List<LikesDetailDto.Response> likesDetails;
    }
}
