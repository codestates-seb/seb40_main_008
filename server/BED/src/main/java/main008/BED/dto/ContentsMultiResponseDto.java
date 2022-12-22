package main008.BED.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor
public class ContentsMultiResponseDto<T, W> {

    private T contentInfo;

    private W curriculumInfo;

    public ContentsMultiResponseDto(T contentInfo, W curriculumInfo) {
        this.contentInfo = contentInfo;
        this.curriculumInfo = curriculumInfo;
    }
}
