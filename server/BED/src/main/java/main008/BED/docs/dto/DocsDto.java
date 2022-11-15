package main008.BED.docs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.List;


public class DocsDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        private MultipartFile file;
        private String details;

    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        private MultipartFile file;
        private String details;
    }

    @Getter
    @AllArgsConstructor
    public static class SingleResponseDto<T> {


        private final T message;
    }

    @Getter
    @AllArgsConstructor
    public static class ListDocsResponseDto {

        private List<ReadAllDto> readAllList;
    }

    @Getter
    @AllArgsConstructor
    public static class ReadAllDto {

        private Long docsId;
        private String name;
    }

}
