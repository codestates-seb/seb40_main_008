package main008.BED.uploadClass.controller;

import lombok.RequiredArgsConstructor;
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

import java.io.IOException;

@RestController
@RequestMapping("lecture")
@RequiredArgsConstructor
public class UploadClassController {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

    private final ChapterRepository chapterRepository;
    private final DocsService docsService;
    private final DocsMapper docsMapper;


    /**
     * Create - 영상 & 강의 자료 올리기
     */
    @PostMapping("{chapter-id}")
    public ResponseEntity postUploadClass(@RequestParam("videoFile") MultipartFile videoFile,
                                          @RequestParam("title") String title,
                                          @RequestParam("docsFile") MultipartFile docsFile,
                                          @RequestParam("details") String details,
                                          @PathVariable("chapter-id") Long chapterId) throws IOException {
        Chapter chapter = chapterRepository.findById(chapterId).get();
        DocsDto.Post docsPost = new DocsDto.Post(docsFile, details);
        Docs docs = docsService.saveDocs(docsMapper.postDtoToEntity(docsPost));
        UploadClassDto.Post post = new UploadClassDto.Post(videoFile, title, chapter, docs);
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
        // TODO 1: 클라이언트 측에서 바로 실행 중지
        // TODO 2: {커리큘럼, 수업자료, 댓글, 메모하기} <- 포함해서 비디오까지 DTO에 담아서 한 번에 보낼 것인지 생각해봐야 함.
        // TODO 3: 멀티 쓰레드 활용 -> 비디오 전송, 나머지 자료 전송 각각 따로

//        ThreadStream threadStream = new ThreadStream();
//        threadStream.start();

        return Mono.just(uploadClassService.prepareContent(fileName, range));
    }

//    /**
//     * Thread
//     */
//    public class ThreadStream extends Thread {
//
//        private String fileName;
//        private String range;
//        public Mono<ResponseEntity<byte[]>> run() {
//            return Mono.just(uploadClassService.prepareContent(fileName, range));
//        }
//
//        public ThreadStream(String fileName, String range) {
//            this.fileName = fileName;
//            this.range = range;
//        }
//    }
}
