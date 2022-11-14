package main008.BED.userPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.userPage.mapper.UserPageMapper;
import main008.BED.userPage.service.UserPageService;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserPageController {

    private final UserPageService userPageService;
    private final UserPageMapper userPageMapper;
}
