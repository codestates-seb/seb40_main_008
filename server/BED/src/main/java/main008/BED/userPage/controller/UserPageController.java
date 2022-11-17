package main008.BED.userPage.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.userPage.entity.UserPage;
import main008.BED.userPage.mapper.UserPageMapper;
import main008.BED.userPage.service.UserPageService;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class UserPageController {

    private final UserPageService userPageService;
    private final UserPageMapper userPageMapper;

    // 생성 UsersService에서 생성

    // 수정

    // 조회
    @GetMapping("/auth/home/{users-id}")
    public ResponseEntity getUserPage(@PathVariable("users-id") @Positive Long usersId) {

        UserPage userPage = userPageService.findUserPage(usersId);

        return new ResponseEntity<>(userPageMapper.userPageToResponse(userPage), HttpStatus.OK);
    }

    // 삭제
}
