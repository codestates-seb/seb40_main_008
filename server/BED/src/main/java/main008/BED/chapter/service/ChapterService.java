package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.mapper.ChapterMapperImpl;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.repository.DocsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.uploadClass.entity.UploadClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ContentsRepository contentsRepository;
    private final DocsRepository docsRepository;
    private final ChapterMapperImpl chapterMapper;

    /**
     * SAVE
     */
    public Chapter saveChapter(Chapter chapter, Long contentsId) {

        Contents byContentsId = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        /*
        List<Chapter> chapterList = byContentsId.getChapterList();
        chapterList.add(chapter);
        byContentsId.setChapterList(chapterList);

        chapter.setContents(byContentsId);
        */

        chapter.addContents(byContentsId);
        Chapter save = chapterRepository.save(chapter);
        return save;
    }

    /**
     * READ ONE
     */
    public Chapter readOne(Long chapterId) {

        existChapter(chapterId);

        return chapterRepository.findById(chapterId).get();
    }

    /**
     * UPDATE
     */
    public void updateChapter(Long chapterId, Chapter newChapter) {

        existChapter(chapterId);

        Chapter oldChapter = chapterRepository.findByChapterId(chapterId);
        oldChapter.setTitle(newChapter.getTitle());
        oldChapter.setFileKey(newChapter.getFileKey());
        oldChapter.setThumbnail(newChapter.getThumbnail());
        oldChapter.setChapterOrder(newChapter.getChapterOrder());
    }

    /**
     * DELETE
     */
    public void removeChapter(Chapter chapter) {

        List<UploadClass> uploadClassList = chapter.getUploadClassList();

        List<Docs> docsListInChapter = uploadClassList.stream()
                .map(uploadClass -> uploadClass.getDocs())
                .collect(Collectors.toList());

        docsListInChapter.stream()
                         .forEach(docs -> docsRepository.delete(docs)); // Docs 연쇄 삭제

        chapterRepository.delete(chapter); // UploadClass, Review - cascade 연쇄 삭제
    }


    /**
     * Read: 컨텐츠 상세화면 커리큘럼 읽어오기 - 컨텐츠 API에서 호출
     */
    public ChapterDto.CurriculumInContent readCurriculumInContent(Long contentsId) {

        Contents contents = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

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

        Contents contents = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        List<Chapter> chapterList = contents.getChapterList();

        List<ChapterDto.ResponseDtoWithoutThumbnail> responseList = chapterList.stream()
                .map(chapter -> chapterMapper.entityToResponseDtoWithoutThumbnail(chapter))
                .collect(Collectors.toList());

        ChapterDto.CurriculumInStream curriculumInStream = new ChapterDto.CurriculumInStream(responseList);

        return curriculumInStream;
    }

    private void existChapter(Long chapterId) {

        if (!chapterRepository.existsByChapterId(chapterId)) {
            throw new BusinessLogicException(ExceptionCode.CHAPTER_NOT_FOUND);
        }
    }
}

