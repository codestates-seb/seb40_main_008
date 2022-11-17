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

import java.util.HashMap;

@RestController
@RequestMapping("chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    private final S3ServiceImpl s3ServiceImpl;


    @PostMapping()
    public ResponseEntity postChapter(@RequestParam("thumbnail") MultipartFile thumbnail,
                                      @RequestParam("chapterOrder") String chapterOrder,
                                      @RequestParam("title") String title) {

        HashMap map = s3ServiceImpl.uploadToS3(thumbnail, "/chapter/thumbnail");
        String url = map.get("url").toString();
        String keys = map.get("keys").toString();

        ChapterDto.Post post = new ChapterDto.Post(chapterOrder, title, url, keys);
        Chapter chapter = chapterMapper.postDtoToEntity(post);
        chapterService.saveChapter(chapter);
        return new ResponseEntity("Chapter is successfully saved.", HttpStatus.CREATED);
    }



    @DeleteMapping("/del/{chapter-id}")
    public ResponseEntity deleteChapter(@PathVariable("chapter-id") Long id) {
        Chapter chapter = chapterService.findOne(id);
        String keys = chapter.getKeys();
        s3ServiceImpl.delete(keys, "/chapter/thumbnail");
        chapterService.removeChapter(chapter);
        return new ResponseEntity("The Chapter is removed.", HttpStatus.OK);
    }

}
