package main008.BED.uploadClass.dto;

import lombok.*;
import main008.BED.chapter.entity.Chapter;
import main008.BED.docs.entity.Docs;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class UploadClassDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        private String video;
        private String title;
        private String name;
        private String fileKey;
        private Docs docs;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {
        private String video;
        private String title;
        private String name;
        private String fileKey;
        private Chapter chapter;
        private Docs docs;

    }

    @Getter
    @AllArgsConstructor
    public static class SingleResponseDto<T> {
        private final T message;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StreamPageResponseDto {

        private ResponseEntity<byte[]> video;
        private Docs docs;
    }

    @Getter
    @AllArgsConstructor
    public static class ResponseDtoInChapter {

        private Long uploadClassId;
        private String title;
    }



}
