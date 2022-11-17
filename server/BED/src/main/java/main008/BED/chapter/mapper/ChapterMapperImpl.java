package main008.BED.chapter.mapper;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.mapper.UploadClassMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ChapterMapperImpl implements ChapterMapper{

    private final UploadClassMapper uploadClassMapper;

    @Override
    public Chapter postDtoToEntity(ChapterDto.Post post) {

        if ( post == null ) {
            return null;
        }

        Chapter chapter = new Chapter();

        chapter.setChapterOrder( post.getChapterOrder() );
        chapter.setTitle( post.getTitle() );
        chapter.setThumbnail( post.getThumbnail() );
        chapter.setFileKey( post.getKeys() );


        return chapter;
    }

    @Override
    public ChapterDto.ResponseDto entityToResponseDto(Chapter chapter) {

        List<UploadClass> uploadClassList = chapter.getUploadClassList();
        List<UploadClassDto.ResponseDtoInChapter> collect = uploadClassList.stream()
                .map(uploadClass -> uploadClassMapper.entityToResponseDtoInChapter(uploadClass))
                .collect(Collectors.toList());

        return new ChapterDto.ResponseDto()
                .builder()
                .chapterId(chapter.getChapterId())
                .chapterOrder(chapter.getChapterOrder())
                .title(chapter.getTitle())
                .thumbnail(chapter.getThumbnail())
                .uploadClassList(collect)
                .build();
    }
}
