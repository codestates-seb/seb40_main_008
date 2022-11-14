package main008.BED.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.bookmark.mapper.BookmarkMapper;
import main008.BED.bookmark.service.BookmarkService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;
}
