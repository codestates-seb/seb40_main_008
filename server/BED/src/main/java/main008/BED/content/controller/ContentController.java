package main008.BED.content.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.content.mapper.ContentMapper;
import main008.BED.content.service.ContentService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;
    private final ContentMapper contentMapper;

}
