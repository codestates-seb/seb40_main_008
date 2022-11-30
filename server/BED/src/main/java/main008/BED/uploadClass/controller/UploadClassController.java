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
@RequestMapping
@RequiredArgsConstructor
public class UploadClassController {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

    private final ChapterRepository chapterRepository;
    private final DocsService docsService;
    private final DocsMapper docsMapper;

    private final S3ServiceImpl s3Service;


    /**
     * Post - 영상 & 강의 자료 올리기
     */
    @PostMapping("auth/chapter/lecture/{chapter-id}")
    public ResponseEntity postUploadClass(@RequestParam("videoFile") MultipartFile videoFile,
                                          @RequestParam("title") String title,
                                          @RequestParam("docsFile") MultipartFile docsFile,
                                          @RequestParam("details") String details,
                                          @PathVariable("chapter-id") Long chapterId) throws IOException {

        DocsDto.Post docsPost = new DocsDto.Post(docsFile, details);
        Docs docs = docsService.saveDocs(docsMapper.postDtoToEntity(docsPost));


        HashMap map = s3Service.uploadToS3(videoFile, "/UploadClass/video");
        String videoUrl = map.get("url").toString();
        String fileKey = map.get("fileKey").toString();
        String videoName = videoFile.getOriginalFilename();

        UploadClassDto.Post post = new UploadClassDto.Post(videoUrl, title, videoName, fileKey, docs);
        uploadClassService.saveLecture(uploadClassMapper.postDtoToEntity(post), chapterId);
        return new ResponseEntity(new UploadClassDto.SingleResponseDto("Uploading Lecture is completed."),
                HttpStatus.CREATED);
    }

    /**
     * Patch: 영상 & 자료 수정하기
     */
    @PatchMapping("auth/chapter/lecture/{uploadClass-id}")
    public ResponseEntity patchUploadClass(@RequestParam(value = "videoFile") MultipartFile videoFile,
                                           @RequestParam(value = "title") String newTitle,
                                           @RequestParam(value = "docsFile") MultipartFile docsFile,
                                           @RequestParam(value = "details") String details,
                                           @PathVariable("uploadClass-id") @Positive Long oldUploadClassId) throws IOException {

        UploadClass oldUploadClass = uploadClassService.readClassById(oldUploadClassId);
        Chapter oldChapter = oldUploadClass.getChapter();
        String oldFileKey = oldUploadClass.getFileKey();
        String newVideoName = videoFile.getOriginalFilename();
        Long oldDocsId = oldUploadClass.getDocs().getDocsId();

        // Docs Table update
        DocsDto.Patch patchDocs = new DocsDto.Patch(docsFile, details);
        Docs newDocs = docsMapper.patchDtoToEntity(patchDocs);
        docsService.updateDocs(newDocs, oldDocsId);

        // s3 update
        HashMap map = s3Service.updateToS3(videoFile, "/UploadClass/video", oldFileKey);// update video in S3
        String newVideoUrl = map.get("url").toString();
        String newFileKey = map.get("fileKey").toString();

        // UploadClass Table update
        UploadClassDto.Patch patchUploadClass =
                new UploadClassDto.Patch(newVideoUrl, newTitle, newVideoName, newFileKey, oldChapter, newDocs);
        UploadClass newUploadClass = uploadClassMapper.patchDtoToEntity(patchUploadClass);
        uploadClassService.updateLecture(oldUploadClassId, newUploadClass);

        return new ResponseEntity("The Lecture is updated.", HttpStatus.OK);
    }

    /**
     * Delete: 영상 & 자료 삭제하기
     */
    @DeleteMapping("auth/chapter/lecture/{uploadClass-id}")
    public ResponseEntity deleteUploadClass(@PathVariable("uploadClass-id") @Positive Long uploadClassId) {
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);
        uploadClassService.removeClassById(uploadClassId);
        s3Service.delete(uploadClass.getFileKey(), "/UploadClass/video");
        return new ResponseEntity("The Lecture is removed.", HttpStatus.OK);
    }
}
