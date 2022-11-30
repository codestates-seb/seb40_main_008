package main008.BED.myUploadClass.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main008.BED.contents.dto.ContentsDto;

import java.util.List;

public class MyUploadClassDto {

    @Getter
    @AllArgsConstructor
    public static class Response {

        private List<ContentsDto.Response> contentsList;
    }
}
