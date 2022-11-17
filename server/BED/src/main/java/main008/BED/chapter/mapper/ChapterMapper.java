package main008.BED.chapter.mapper;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.uploadClass.entity.UploadClass;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ChapterMapper {


    Chapter postDtoToEntity(ChapterDto.Post post);

    ChapterDto.ResponseDto entityToResponseDto(Chapter chapter);
}
