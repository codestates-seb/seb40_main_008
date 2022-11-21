package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3Service;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.HashMap;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class ContentsController {

    private final ContentsService contentsService;
    private final ContentsMapper contentsMapper;
    private final WishMapper wishMapper;
    private final S3Service s3Service;


    // 컨텐츠 개설
    @PostMapping("/auth/{users-id}/uploadcontents")
    public ResponseEntity postContents(@PathVariable("users-id") @Positive Long usersId,
                                       @RequestParam("title") String title,
                                       @RequestParam("categories") Contents.Categories categories,
                                       @RequestParam("details") String details,
                                       @RequestParam("tutorDetail") String tutorDetail,
                                       @RequestParam("thumbnail") MultipartFile thumbnail) {

        // thumbnail -> S3 업로드
        HashMap map = s3Service.uploadToS3(thumbnail, "/contents/thumbnail");
        String fileKey = map.get("fileKey").toString();
        String thumbnailUrl = map.get("url").toString();
//
        ContentsDto.Post post = new ContentsDto.Post(title, categories, details, tutorDetail, thumbnailUrl, fileKey);

        Contents contents = contentsService.createContents(contentsMapper.postToContents(post), usersId);

        return new ResponseEntity<>(contentsMapper.contentsToResponse(contents), HttpStatus.CREATED);
    }


    // 컨텐츠 찜 기능
    @PostMapping("/auth/{users-id}/{contents-id}/wish")
    public ResponseEntity wishContents(@PathVariable("users-id") @Positive Long usersId,
                                       @PathVariable("contents-id") @Positive Long contentsId,
                                       @Valid @RequestBody WishDto.Post post) {

        Wish wish = wishMapper.postToWish(post);

        contentsService.wishContents(contentsId, usersId, wish);

//        return new ResponseEntity<>(response, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body("Update your wishlist.");
    }
}
