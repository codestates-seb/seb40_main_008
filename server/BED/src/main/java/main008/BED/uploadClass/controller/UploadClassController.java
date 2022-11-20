package main008.BED.uploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.repository.ChapterRepository;
import main008.BED.docs.dto.DocsDto;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.docs.service.DocsService;
import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.mapper.UploadClassMapper;
import main008.BED.uploadClass.service.UploadClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Positive;
import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("auth/chapter/lecture")
@RequiredArgsConstructor
public class UploadClassController {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

    private final ChapterRepository chapterRepository;
    private final DocsService docsService;
    private final DocsMapper docsMapper;

    private final S3ServiceImpl s3Service;


    /**
     * Create - 영상 & 강의 자료 올리기
     */
    @PostMapping("{chapter-id}")
    public ResponseEntity postUploadClass(@RequestParam("videoFile") MultipartFile videoFile,
                                          @RequestParam("title") String title,
                                          @RequestParam("docsFile") MultipartFile docsFile,
                                          @RequestParam("details") String details,
                                          @PathVariable("chapter-id") Long chapterId) throws IOException {

        DocsDto.Post docsPost = new DocsDto.Post(docsFile, details);
        Docs docs = docsService.saveDocs(docsMapper.postDtoToEntity(docsPost));

        Chapter chapter = chapterRepository.findById(chapterId).get();

        HashMap map = s3Service.uploadToS3(videoFile, "/UploadClass/video");
        String videoUrl = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();
        String videoName = videoFile.getOriginalFilename();

        UploadClassDto.Post post = new UploadClassDto.Post(videoUrl, title, videoName, fileKey, chapter, docs);
        uploadClassService.saveLecture(uploadClassMapper.postDtoToEntity(post));
        return new ResponseEntity(new UploadClassDto.SingleResponseDto("Uploading Lecture is completed."),
                HttpStatus.CREATED);
    }

    /**
     * Delete: 영상 & 자료 삭제하기
     */
    @DeleteMapping("del/{uploadClass-id}")
    public ResponseEntity deleteUploadClass(@PathVariable("uploadClass-id") @Positive Long uploadClassId) {
        uploadClassService.removeClassById(uploadClassId);
        return new ResponseEntity("The Lecture is removed.", HttpStatus.OK);
    }






}
