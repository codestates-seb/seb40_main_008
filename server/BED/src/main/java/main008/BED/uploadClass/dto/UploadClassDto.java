package main008.BED.uploadClass.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

public class UploadClassDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String title;
        private MultipartFile video;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

    }
}
