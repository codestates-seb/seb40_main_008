package main008.BED.warning.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class WarningDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {

        private String reason;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

    }
}
