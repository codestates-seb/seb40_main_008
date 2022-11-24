package main008.BED.chapter.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.mapper.ChapterMapper;
import main008.BED.chapter.service.ChapterService;
import main008.BED.contents.service.ContentsService;
import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.mapper.UploadClassMapper;
import main008.BED.uploadClass.repository.UploadClassRepository;
import main008.BED.uploadClass.service.UploadClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Positive;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth/contents")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    private final ContentsService contentsService;

    private final UploadClassService uploadClassService;
    private final S3ServiceImpl s3ServiceImpl;


    /**
     * Create: 챕터 생성
     */
    @PostMapping("/chapter/{contents-id}")
    public ResponseEntity postChapter(@PathVariable("contents-id") @Positive Long contentsId,
                                      @RequestParam("thumbnail") MultipartFile thumbnail,
                                      @RequestParam("chapterOrder") String chapterOrder,
                                      @RequestParam("title") String title) {

        HashMap map = s3ServiceImpl.uploadToS3(thumbnail, "/chapter/thumbnail");
        String url = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();

        ChapterDto.Post post = new ChapterDto.Post(chapterOrder, title, url, fileKey);
        Chapter chapter = chapterMapper.postDtoToEntity(post);
        chapterService.saveChapter(chapter, contentsId);
        return new ResponseEntity("The Chapter is successfully saved.", HttpStatus.CREATED);
    }



//    /**
//     * GET: 콘텐츠 상세화면의 커리큘럼
//     */
//    @GetMapping("/curriculum/{contents-id}")
//    public ResponseEntity getCurriculumInContent(@PathVariable("contents-id") @Positive Long contentsId) {
//        List<Chapter> chapters = contentsService.readChapterList(contentsId);
//        List<ChapterDto.ResponseDto> responseList = chapters.stream()
//                .map(chapter -> chapterMapper.entityToResponseDto(chapter))
//                .collect(Collectors.toList());
//        ChapterDto.CurriculumInContent curriculumInContent = new ChapterDto.CurriculumInContent(responseList);
//        return new ResponseEntity(curriculumInContent, HttpStatus.OK);
//    }
//
//    /**
//     * GET: 동영상 재생화면의 커리큘럼
//     */
//    @GetMapping("/streaming/curriculum/{contents-id}")
//    public ResponseEntity getCurriculumInStream(@PathVariable("contents-id") @Positive Long contentsId) {
//        List<Chapter> chapters = contentsService.readChapterList(contentsId);
//        List<ChapterDto.ResponseDtoWithoutThumbnail> responseDtoWithoutThumbnails = chapters.stream()
//                .map(chapter -> chapterMapper.entityToResponseDtoWithoutThumbnail(chapter))
//                .collect(Collectors.toList());
//        ChapterDto.CurriculumInStream curriculumInStream = new ChapterDto.CurriculumInStream(responseDtoWithoutThumbnails);
//        return new ResponseEntity(curriculumInStream, HttpStatus.OK);
//    }
//
//    /**
//     * GET: 하나의 Chapter 목록 리스트 (TEST 용)
//     */
//    @GetMapping("/chapter/{chapter-id}")
//    public ResponseEntity getChapter(@PathVariable("chapter-id") @Positive Long chapterId) {
//
//        Chapter chapter = chapterService.readOne(chapterId);
//        List<UploadClass> uploadClassList = uploadClassService.readAll();
//        chapter.setUploadClassList(uploadClassList);
//        ChapterDto.ResponseDto responseDto = chapterMapper.entityToResponseDto(chapter);
//        return new ResponseEntity(responseDto, HttpStatus.OK);
//    }

    /**
     * Patch: 챕터 수정
     */
    @PatchMapping("/chapter/{chapter-id}")
    public ResponseEntity patchChapter(@PathVariable("chapter-id") @Positive Long oldChapterId,
                                       @RequestParam("thumbnail") MultipartFile thumbnail,
                                       @RequestParam("chapterOrder") String chapterOrder,
                                       @RequestParam("title") String title) {

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
