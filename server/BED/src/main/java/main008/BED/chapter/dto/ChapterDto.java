package main008.BED.chapter.dto;

import lombok.*;
import main008.BED.uploadClass.dto.UploadClassDto;
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
        private String fileKey;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private String chapterOrder;
        private String title;
        private String thumbnail;
        private String fileKey;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDto {

        private Long chapterId;
        private String chapterOrder;
        private String title;
        private String thumbnail;
        private List<UploadClassDto.ResponseDtoInChapter> uploadClassList;

    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResponseDtoWithoutThumbnail {

        private Long chapterId;
        private String chapterOrder;
        private String title;
        private List<UploadClassDto.ResponseDtoInChapter> uploadClassList;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CurriculumInContent {
        List<ChapterDto.ResponseDto> curriculumInfo;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CurriculumInStream {
        List<ChapterDto.ResponseDtoWithoutThumbnail> curriculumInfo;
    }


}
