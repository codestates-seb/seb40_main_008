package main008.BED.chapter.mapper;

import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChapterMapper {

    Chapter postDtoToEntity(ChapterDto.Post post);
}
