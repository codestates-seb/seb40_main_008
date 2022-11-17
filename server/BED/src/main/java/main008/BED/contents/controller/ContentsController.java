package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import main008.BED.wish.dto.WishDto;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.mapper.WishMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/auth/home")
@RequiredArgsConstructor
@Validated
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsMapper contentsMapper;
    private final WishMapper wishMapper;


    // 컨텐츠 개설
    @PostMapping("/{users-id}/uploadcontents")
//    @PostMapping("/mypage/uploadcontents")
    public ResponseEntity postContents(@PathVariable("users-id") @Positive Long usersId,
                                       @Valid @RequestBody ContentsDto.Post post) {



        Contents contents = contentsService.createContents(contentsMapper.postToContents(post), usersId);

        return new ResponseEntity<>(contentsMapper.contentsToResponse(contents), HttpStatus.CREATED);
    }

    /*
    // 컨텐츠 수정
    @PatchMapping("/{users-id}/uploadcontents/{contents-id}")
    public ResponseEntity patchContents(@PathVariable("users-id") @Positive Long userId,
                                        @PathVariable("contents-id") @Positive Long contentsId,
                                        @Valid @RequestBody ContentsDto.Patch patch) {


    }
    */

    // 컨텐츠 조회
//    @GetMapping

    // 컨텐츠 찜 기능
    @PostMapping("/{users-id}/{contents-id}/wish")
    public ResponseEntity wishContents(@PathVariable("users-id") @Positive Long usersId,
                                       @PathVariable("contents-id") @Positive Long contentsId,
                                       @Valid @RequestBody WishDto.Post post) {

        Wish wish = wishMapper.postToWish(post);

        contentsService.wishContents(contentsId, usersId, wish);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
