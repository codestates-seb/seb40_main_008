package main008.BED.chapter.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping("auth/contents")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;
    private final S3ServiceImpl s3ServiceImpl;


    /**
     * Create: 챕터 생성
     */
    @PostMapping("/chapter/{contents-id}")
    public ResponseEntity postChapter(@PathVariable("contents-id") @Positive Long contentsId,
                                      @RequestParam("thumbnail") MultipartFile thumbnail,
                                      @RequestParam("chapterOrder") String chapterOrder,
                                      @RequestParam("title") String title) throws UnsupportedEncodingException {

        HashMap map = s3ServiceImpl.uploadToS3(thumbnail, "/chapter/thumbnail");
        String url = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();

        ChapterDto.Post post = new ChapterDto.Post(chapterOrder, title, url, fileKey);
        Chapter chapter = chapterMapper.postDtoToEntity(post);
        chapterService.saveChapter(chapter, contentsId);
        return new ResponseEntity("The Chapter is successfully saved.", HttpStatus.CREATED);
    }



    /**
     * Patch: 챕터 수정
     */
    @PatchMapping("/chapter/{chapter-id}")
    public ResponseEntity patchChapter(@PathVariable("chapter-id") @Positive Long oldChapterId,
                                       @RequestParam("thumbnail") MultipartFile thumbnail,
                                       @RequestParam("chapterOrder") String chapterOrder,
                                       @RequestParam("title") String title) throws UnsupportedEncodingException {

        Chapter oldChapter = chapterService.readOne(oldChapterId);

        // s3 thumbnail Update
        HashMap map = s3ServiceImpl.updateToS3(thumbnail, "/chapter/thumbnail", oldChapter.getFileKey());
        String url = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();

        // chapter update
        ChapterDto.Patch patch = new ChapterDto.Patch(chapterOrder, title, url, fileKey);
        Chapter newChapter = chapterMapper.patchDtoToEntity(patch);
        chapterService.updateChapter(oldChapterId, newChapter);
        return new ResponseEntity("The Chapter is updated.", HttpStatus.OK);
    }



    /**
     * Delete: 챕터 삭제
     */
    @DeleteMapping("/chapter/{chapter-id}")

    public ResponseEntity deleteChapter(@PathVariable("chapter-id") Long id) {
        Chapter chapter = chapterService.readOne(id);
        String fileKey = chapter.getFileKey();
        s3ServiceImpl.delete(fileKey, "/chapter/thumbnail");
        chapterService.removeChapter(chapter);
        return new ResponseEntity("The Chapter is removed.", HttpStatus.OK);
    }

}
