package main008.BED.userPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.mapper.UserPageMapper;
import main008.BED.userPage.service.UserPageService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserPageController {

    private final UserPageService userPageService;
    private final UserPageMapper userPageMapper;
    private final UsersService usersService;

    // 생성 UsersService에서 생성

    // 수정

    // 조회
    @GetMapping("/auth/mypage")
    public ResponseEntity getUserPage(Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        UserPage userPage = userPageService.findUserPage(users.getUsersId());

        return new ResponseEntity<>(userPageMapper.userPageToResponse(userPage), HttpStatus.OK);
    }

    // 삭제
}
