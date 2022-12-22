package main008.BED.users.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.dto.SingleResponseDto;
import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

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

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity getUser(Principal principal){

        Users user = usersService.findVerifiedUserByEmail(principal.getName());

        return new ResponseEntity<>(
                new SingleResponseDto<>(usersMapper.usersToMyPage(user)), HttpStatus.OK);
    }

    @PatchMapping("/auth/userinfo")
    public ResponseEntity patchUser(@Valid @RequestBody UsersDto.Patch userPatchDto,
                                    Principal principal){

        Users user = usersService.patchUser(
                usersMapper.usersPatchDtoToUser(userPatchDto), principal);

        return new ResponseEntity<>(
                new SingleResponseDto<>(usersMapper.usersToUserResponseDto(user)), HttpStatus.OK);
    }

    @DeleteMapping("/auth/userinfo")
    public ResponseEntity deleteUser(Principal principal){

        usersService.deleteUser(principal.getName());

        return new ResponseEntity<>("Your account has been deleted.", HttpStatus.OK);
    }
}
