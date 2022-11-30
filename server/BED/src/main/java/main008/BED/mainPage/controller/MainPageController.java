package main008.BED.mainPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.mainPage.mapper.MainPageMapper;
import main008.BED.mainPage.service.MainPageService;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;
    private final MainPageMapper mainPageMapper;
    private final ContentsMapper contentsMapper;
    private final UsersMapper usersMapper;
    private final UsersService usersService;

    // 로그인 전
    @GetMapping("/home")
    public ResponseEntity getNotLoginHome() {

        MainPage mainPage = mainPageService.getHome();

        return new ResponseEntity<>(
                mainPageMapper.mainPageToNotLoginResponse(mainPage, contentsMapper, usersMapper), HttpStatus.OK);
    }

    // 로그인 후
    @GetMapping("/auth/home")
    public ResponseEntity getLoginHome(Principal principal) {

        MainPage mainPage = mainPageService.getHome();
        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        return new ResponseEntity<>(
                mainPageMapper.mainPageToLoginResponse(
                        mainPage, contentsMapper, users.getUsersId(), usersMapper), HttpStatus.OK);
    }
}
