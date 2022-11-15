package main008.BED.uploadClass.controller;

import lombok.RequiredArgsConstructor;
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

import java.io.IOException;

@RestController
@RequestMapping("lecture")
@RequiredArgsConstructor
public class UploadClassController {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

    private final DocsService docsService;
    private final DocsMapper docsMapper;


    /**
     * Create - 영상 저장
     */
    @PostMapping()
    public ResponseEntity postUploadClass(@RequestParam("videoFile") MultipartFile videofile,
                                          @RequestParam("title") String title,
                                          @RequestParam("docsFile") MultipartFile docsFile,
                                          @RequestParam("details") String details) throws IOException {

        DocsDto.Post docsPost = new DocsDto.Post(docsFile, details);
        Docs docs = docsService.saveDocs(docsMapper.postDtoToEntity(docsPost));
        UploadClassDto.Post post = new UploadClassDto.Post(videofile, title, docs);
        uploadClassService.saveVideo(uploadClassMapper.postDtoToEntity(post));
        return new ResponseEntity(new UploadClassDto.SingleResponseDto("Uploading Lecture is completed."),
                HttpStatus.CREATED);
    }



    /**
     * Stream - 영상 시청 페이지 부분 전송
     */
    @GetMapping("stream/{file-name}")
    public Mono<ResponseEntity<byte[]>> streamVideo(@RequestHeader(value = "Range", required = false) String range,
                                                    @PathVariable("file-name") String fileName) {

        return Mono.just(uploadClassService.prepareContent(fileName, range));
    }
}
