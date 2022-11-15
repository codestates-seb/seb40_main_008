package main008.BED.users.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;
    private final UsersMapper usersMapper;

    // 임시 회원가입
    @PostMapping("/home/sign-up")
    public ResponseEntity postUser(@Valid @RequestBody UsersDto.Post post) {

        Users users = usersService.createUsers(usersMapper.postToEntity(post));

        UsersDto.UserResponseToMyPage response = usersMapper.usersToMyPage(users);

        return new ResponseEntity<>("welcome", HttpStatus.CREATED);
    }
}
