package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsMapper contentsMapper;
}
