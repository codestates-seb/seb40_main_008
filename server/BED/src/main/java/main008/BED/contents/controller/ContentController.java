package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.mapper.ContentMapper;
import main008.BED.contents.service.ContentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final ContentMapper contentMapper;

}
