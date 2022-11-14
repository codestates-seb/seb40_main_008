package main008.BED.chapter.service;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.repository.ChapterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChapterService {
    private final ChapterRepository chapterRepository;
}
