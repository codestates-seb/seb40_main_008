package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.repository.ChapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;

    /**
     * CREATE
     *
     * @return
     */
    public Chapter saveChapter(Chapter chapter) {
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
