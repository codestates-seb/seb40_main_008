package main008.BED.search.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.search.mapper.SearchMapper;
import main008.BED.search.service.SearchService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;
    private final SearchMapper searchMapper;
}
