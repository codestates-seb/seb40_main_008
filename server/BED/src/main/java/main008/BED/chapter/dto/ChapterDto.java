package main008.BED.chapter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import main008.BED.uploadClass.entity.UploadClass;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ChapterDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private String chapterOrder;

        private String title;

        private String thumbnail;

        private String keys;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

    }

    @Getter
    @AllArgsConstructor
    public static class ResponseDto {

        private String chapterOrder;
        private String title;
        private String thumbnail;
        private List<UploadClass> uploadClassList;

    }


}
