package main008.BED.docs.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.docs.service.DocsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class DocsController {

    private final DocsService docsService;
    private final DocsMapper docsMapper;
}
