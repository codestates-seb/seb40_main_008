package main008.BED.class_index.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.class_index.mapper.ClassIndexMapper;
import main008.BED.class_index.service.ClassIndexService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClassIndexController {

    private final ClassIndexService classIndexService;
    private final ClassIndexMapper classIndexMapper;
}
