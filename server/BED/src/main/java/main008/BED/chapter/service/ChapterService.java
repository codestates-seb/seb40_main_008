package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.exception.ChapterNotFoundException;
import main008.BED.chapter.exception.ContentsNotFoundException;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.mapper.ChapterMapperImpl;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ContentsRepository contentsRepository;

    private final ChapterMapperImpl chapterMapper;



    /**
     * SAVE
     */
    public Chapter saveChapter(Chapter chapter, Long contentsId) {

        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new ContentsNotFoundException();
        }
        Contents byContentsId = contentsRepository.findByContentsId(contentsId);

        List<Chapter> chapterList = byContentsId.getChapterList();
        chapterList.add(chapter);
        byContentsId.setChapterList(chapterList);

        chapter.setContents(byContentsId);
        Chapter save = chapterRepository.save(chapter);
        return save;
    }

    /**
     * READ ONE
     */
    public Chapter readOne(Long chapterId) {
        if (!chapterRepository.existsByChapterId(chapterId)) {
            throw new ChapterNotFoundException();
        }
        return chapterRepository.findById(chapterId).get();
    }

    /**
     * UPDATE
     */
    public void updateChapter(Long chapterId, Chapter newChapter) {
        if (!chapterRepository.existsByChapterId(chapterId)) {
            throw new ChapterNotFoundException();
        }
        Chapter oldChapter = chapterRepository.findByChapterId(chapterId);
        oldChapter.setChapterOrder(newChapter.getChapterOrder());
        oldChapter.setTitle(newChapter.getTitle());
        oldChapter.setThumbnail(newChapter.getThumbnail());
        oldChapter.setFileKey(newChapter.getFileKey());
    }

    /**
     * DELETE
     */
    public void removeChapter(Chapter chapter) {
        chapterRepository.delete(chapter);
    }


    /**
     * Read: 컨텐츠 상세화면 커리큘럼 읽어오기 - 컨텐츠 API에서 호출
     */
    public ChapterDto.CurriculumInContent readCurriculumInContent(Long contentsId) {
        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new RuntimeException("The Content with this id is not found.");
        }
        Contents contents = contentsRepository.findByContentsId(contentsId);
        List<Chapter> chapterList = contents.getChapterList();
        List<ChapterDto.ResponseDto> responseList = chapterList.stream()
                .map(chapter -> chapterMapper.entityToResponseDto(chapter))
                .collect(Collectors.toList());
        ChapterDto.CurriculumInContent curriculumInContent = new ChapterDto.CurriculumInContent(responseList);
        return curriculumInContent;
    }

    /**
     * Read: 영상 재생화면 커리큘럼 읽어오기 - 컨텐츠 API에서 호출
     */
    public ChapterDto.CurriculumInStream readCurriculumInStream(Long contentsId) {
        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new RuntimeException("The Content with this id is not found.");
        }
        Contents contents = contentsRepository.findByContentsId(contentsId);
        List<Chapter> chapterList = contents.getChapterList();
        List<ChapterDto.ResponseDtoWithoutThumbnail> responseList = chapterList.stream()
                .map(chapter -> chapterMapper.entityToResponseDtoWithoutThumbnail(chapter))
                .collect(Collectors.toList());
        ChapterDto.CurriculumInStream curriculumInStream = new ChapterDto.CurriculumInStream(responseList);
        return curriculumInStream;
    }
}

