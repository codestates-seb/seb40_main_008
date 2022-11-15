package main008.BED.chapter.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(/*"/auth/home/mypage/{mypage-id}/contents"*/)
@RequiredArgsConstructor
@Validated
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    @PostMapping(/*"/{contents-id}/uploadchapter"*/)
    public ResponseEntity postChapter(/*@PathVariable("contents-id") @Positive Long contentsId,*/
                                      @Valid @RequestBody ChapterDto.Post post) {

        Chapter chapter = chapterService.createChapter(/*contentsId,*/ chapterMapper.postToChapter(post));

        return new ResponseEntity<>(chapterMapper.chapterToResponse(chapter), HttpStatus.CREATED);
    }
}
