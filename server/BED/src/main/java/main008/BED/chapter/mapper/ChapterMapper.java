package main008.BED.chapter.mapper;

import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;

public interface ChapterMapper {


    Chapter postDtoToEntity(ChapterDto.Post post);

    ChapterDto.ResponseDto entityToResponseDto(Chapter chapter);

    ChapterDto.ResponseDtoWithoutThumbnail entityToResponseDtoWithoutThumbnail(Chapter chapter);

    Chapter patchDtoToEntity(ChapterDto.Patch patch);

    ChapterDto.ResponseListDto entityListToResponseListDto(Chapter chapter);

}
