package main008.BED.chapter.mapper;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.mapper.UploadClassMapper;
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
        chapter.setFileKey( post.getFileKey() );

        return chapter;
    }

    @Override
    public Chapter patchDtoToEntity(ChapterDto.Patch patch) {

        if (patch == null) {
            return null;
        }
        Chapter chapter = new Chapter();

        chapter.setChapterOrder(patch.getChapterOrder());
        chapter.setTitle(patch.getTitle());
        chapter.setThumbnail(patch.getThumbnail());
        chapter.setFileKey(patch.getFileKey());

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

    @Override
    public ChapterDto.ResponseDtoWithoutThumbnail entityToResponseDtoWithoutThumbnail(Chapter chapter) {

        List<UploadClass> uploadClassList = chapter.getUploadClassList();
        List<UploadClassDto.ResponseDtoInChapter> collect = uploadClassList.stream()
                .map(uploadClass -> uploadClassMapper.entityToResponseDtoInChapter(uploadClass))
                .collect(Collectors.toList());

        return new ChapterDto.ResponseDtoWithoutThumbnail()
                .builder()
                .chapterId(chapter.getChapterId())
                .chapterOrder(chapter.getChapterOrder())
                .title(chapter.getTitle())
                .uploadClassList(collect)
                .build();
    }


}
