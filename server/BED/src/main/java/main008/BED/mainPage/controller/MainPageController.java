package main008.BED.mainPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.mainPage.mapper.MainPageMapper;
import main008.BED.mainPage.service.MainPageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;
    private final MainPageMapper mainPageMapper;
}
