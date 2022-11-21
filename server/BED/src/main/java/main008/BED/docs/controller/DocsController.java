package main008.BED.docs.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.dto.DocsDto;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.docs.service.DocsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("auth/contents/chapter/docs")
@RequiredArgsConstructor
public class DocsController {

    private final DocsService docsService;
    private final DocsMapper docsMapper;



    // TODO: 유저 권한 로직 - 회원 가입한 유저만 API 사용 가능



    /**
     * Create
     */
    @PostMapping()
    public ResponseEntity postDocs(@RequestParam("file") MultipartFile file,
                                   @RequestParam("details") String details) throws IOException {

        // TODO: id 전송
        DocsDto.Post post = new DocsDto.Post(file, details);
        Docs docs = docsMapper.postDtoToEntity(post);
        docsService.saveDocs(docs);
        return new ResponseEntity(new DocsDto.SingleResponseDto("The Docs is saved"), HttpStatus.CREATED);
    }

    /**
     * Read - User Downloads
     */
    @GetMapping("{docs-id}")
    public ResponseEntity getDocs(@PathVariable("docs-id") Long id) {

        Docs docs = docsService.readDocs(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docs.getName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new ByteArrayResource(docs.getData()));
    }

    /**
     * Read All - Docs Name List
     */
    @GetMapping("all")
    public ResponseEntity getAllDocsNames() {
        List<Docs> docsList = docsService.readAllDocs();
        return ResponseEntity
                .ok(docsList.stream()
                .map(docs -> docsMapper.entityToReadAllDto(docs))
                .collect(Collectors.toList()));
    }

    /**
     * Patch
     */
    @PatchMapping("{docs-id}")
    public ResponseEntity patchDocs(@PathVariable("docs-id") Long id,
                                    @RequestParam("file") MultipartFile file,
                                    @RequestParam("details") String details) throws IOException {
        // TODO: id 전송
        DocsDto.Patch patch = new DocsDto.Patch(file, details);
        Docs docs = docsMapper.patchDtoToEntity(patch);
        docsService.updateDocs(docs, id);

        return new ResponseEntity(new DocsDto.SingleResponseDto("The Docs is updated"), HttpStatus.OK);

    }


    /**
     * Delete
     */
    @DeleteMapping("{docs-id}")
    public ResponseEntity deleteDocs(@PathVariable("docs-id") Long id) {
        docsService.removeDocs(id);
        return new ResponseEntity<>(new DocsDto.SingleResponseDto("The Docs is removed."), HttpStatus.OK);
    }

}
