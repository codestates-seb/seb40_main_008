package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.exception.ContentsNotFoundException;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final ContentsRepository contentsRepository;

    /**
     * CREATE
     *
     * @return
     */
    public Chapter saveChapter(Chapter chapter, Long contentsId) {

        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new ContentsNotFoundException();
        }
        Contents byContentsId = contentsRepository.findByContentsId(contentsId);
        chapter.setContents(byContentsId);
        Chapter save = chapterRepository.save(chapter);
        return save;
    }

    /**
     * READ ONE
     */
    public Chapter readOne(Long id) {
        return chapterRepository.findById(id).get();
    }

    /**
     * DELETE
     */
    public void removeChapter(Chapter chapter) {
        chapterRepository.delete(chapter);
    }
}

