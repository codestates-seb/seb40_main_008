package main008.BED.upload_class.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.upload_class.mapper.UploadClassMapper;
import main008.BED.upload_class.service.UploadClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UploadClass {

    private final UploadClassService uploadClassService;
    private final UploadClassMapper uploadClassMapper;

}
