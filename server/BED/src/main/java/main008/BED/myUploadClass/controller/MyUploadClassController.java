package main008.BED.myUploadClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.myUploadClass.service.MyUploadClassService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyUploadClassController {

    private final MyUploadClassRepository myUploadClassRepository;
    private final MyUploadClassService myUploadClassService;
}
