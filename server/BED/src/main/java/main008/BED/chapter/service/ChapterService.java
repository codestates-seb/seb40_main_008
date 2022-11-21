package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.exception.ChapterNotFoundException;
import main008.BED.chapter.exception.ContentsNotFoundException;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ContentsRepository contentsRepository;

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
}

