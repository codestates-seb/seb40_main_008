package main008.BED.mainPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.mapper.MainPageMapper;
import main008.BED.mainPage.service.MainPageService;
import main008.BED.users.mapper.UsersMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;
    private final MainPageMapper mainPageMapper;
    private final ContentsMapper contentsMapper;
    private final UsersMapper usersMapper;

    // 로그인 전
    @GetMapping("/home")
    public ResponseEntity getNotLoginHome() {

        MainPage mainPage = mainPageService.getNotLoginHome();

        return new ResponseEntity<>(mainPageMapper.mainPageToNotLoginResponse(mainPage, contentsMapper, usersMapper), HttpStatus.OK);
    }

    // 로그인 후
    @GetMapping("/auth/home")
    public ResponseEntity getLoginHome() {

        return null;
    }
}
