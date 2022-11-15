package main008.BED.docs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;

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

    }

    @Getter
    @AllArgsConstructor
    public static class CreatedResponseDto<T> {

        private final T message;
    }

}
