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
import javax.validation.constraints.Positive;
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

        return new ResponseEntity<>(new SingleResponseDto<>(usersMapper.usersToUserResponseDto(user)), HttpStatus.OK);
    }

    @PatchMapping("/auth/{users-id}/userinfo")
    public ResponseEntity patchUser(@PathVariable("users-id") @Positive Long usersId,
                                    @Valid @RequestBody UsersDto.Patch userPatchDto){

        userPatchDto.setUsersId(usersService.findVerifiedUserByEmail(usersService.getUsers(usersId).getEmail()).getUsersId());

        Users user = usersService.patchUser(usersMapper.usersPatchDtoToUser(userPatchDto));

        return new ResponseEntity<>(new SingleResponseDto<>(usersMapper.usersToUserResponseDto(user)), HttpStatus.OK);
    }

    @DeleteMapping("/auth/{users-id}/userinfo")
    public ResponseEntity deleteUser(@PathVariable("users-id") @Positive Long usersId){

        usersService.deleteUser(usersId);

        return new ResponseEntity<>("Your account has been deleted.", HttpStatus.OK);
    }
}
