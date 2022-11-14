package main008.BED.chapter.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;
}
