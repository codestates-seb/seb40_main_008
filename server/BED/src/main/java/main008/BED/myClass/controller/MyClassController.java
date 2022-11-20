package main008.BED.myClass.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.myClass.dto.MyClassDto;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.mapper.MyClassMapper;
import main008.BED.myClass.service.MyClassService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class MyClassController {

    private final MyClassService myClassService;
    private final MyClassMapper myClassMapper;

    // 내가 찜한 클래스
    @GetMapping("/auth/{users-id}/myclass/wishclass") // principal 가능 시 users-id 필요없음, 토큰과 /auth/로 대체 가능
    public ResponseEntity getMyClass(@PathVariable("users-id") @Positive Long usersId) {

        MyClass myClass = myClassService.getWishClass(usersId);

        MyClassDto.WishClassResponse response = myClassMapper.myClassToWishResponse(myClass);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 수강중인 클래스
}
