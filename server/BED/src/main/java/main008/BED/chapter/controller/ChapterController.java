package main008.BED.chapter.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.mapper.UploadClassMapper;
import main008.BED.uploadClass.repository.UploadClassRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("chapter")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    private final UploadClassRepository uploadClassRepository;
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
        return new ResponseEntity("The Chapter is successfully saved.", HttpStatus.CREATED);
    }


    @GetMapping("{chapter-id}")
    public ResponseEntity getChapter(@PathVariable("chapter-id") Long chapterId) {
        Chapter chapter = chapterService.readOne(chapterId);
        List<UploadClass> uploadClassList = uploadClassRepository.findAll();
        chapter.setUploadClassList(uploadClassList);
        ChapterDto.ResponseDto responseDto = chapterMapper.entityToResponseDto(chapter);
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }




    @DeleteMapping("/del/{chapter-id}")
    public ResponseEntity deleteChapter(@PathVariable("chapter-id") Long id) {
        Chapter chapter = chapterService.readOne(id);
        String keys = chapter.getFileKey();
        s3ServiceImpl.delete(keys, "/chapter/thumbnail");
        chapterService.removeChapter(chapter);
        return new ResponseEntity("The Chapter is removed.", HttpStatus.OK);
    }

}
