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

@RestController
@RequestMapping("docs")
@RequiredArgsConstructor
public class DocsController {

    private final DocsService docsService;
    private final DocsMapper docsMapper;

    /**
     * Create
     */
    @PostMapping()
    public ResponseEntity postDocs(@RequestParam("file") MultipartFile file,
                                   @RequestParam("details") String details) throws IOException {

        DocsDto.Post post = new DocsDto.Post(file, details);
        Docs docs = docsMapper.postDtoToEntity(post);
        docsService.saveDocs(docs);
        return new ResponseEntity(new DocsDto.CreatedResponseDto("The Docs is saved"), HttpStatus.CREATED);
    }

    /**
     * Read
     */
    @GetMapping("{docs-id}")
    public ResponseEntity getDocs(@PathVariable("docs-id") Long id) {

        Docs docs = docsService.getDocs(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docs.getName() + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(new ByteArrayResource(docs.getData()));
    }
}
