package main008.BED.uploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.uploadClass.service.mapper.UploadClassMapper;
import main008.BED.uploadClass.service.UploadClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lecture")
@RequiredArgsConstructor
public class UploadClassController {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

}
