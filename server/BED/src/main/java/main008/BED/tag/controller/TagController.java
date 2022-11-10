package main008.BED.tag.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.tag.mapper.TagMapper;
import main008.BED.tag.service.TagService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;
}
