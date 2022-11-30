package main008.BED.myClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.dto.MyClassDto;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.mapper.MyClassMapper;
import main008.BED.myClass.service.MyClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MyClassController {

    private final MyClassService myClassService;
    private final MyClassMapper myClassMapper;
    private final UsersService usersService;

    // 내가 찜한 클래스
    @GetMapping("/auth/myclass/wishclass") // principal 가능 시 users-id 필요없음, 토큰과 /auth/로 대체 가능
    public ResponseEntity getMyClass(Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        MyClass myClass = myClassService.getWishClass(users.getUsersId());

        MyClassDto.WishClassResponse response = myClassMapper.myClassToWishResponse(myClass);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 수강중인 클래스
    @GetMapping("/auth/myclass/takingclass")
    public ResponseEntity getMyBuyClass(Principal principal) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        MyClass myClass = myClassService.getBuyClass(users.getUsersId());

        MyClassDto.TakingClassResponse response = myClassMapper.myClassToTakingResponse(myClass);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
