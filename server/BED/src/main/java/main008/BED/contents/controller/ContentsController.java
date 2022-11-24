package main008.BED.contents.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3Service;
import main008.BED.chapter.dto.ChapterDto;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.service.ChapterService;
import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.contents.service.ContentsService;
import main008.BED.docs.entity.Docs;
import main008.BED.docs.mapper.DocsMapper;
import main008.BED.payment.dto.PaymentDto;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.mapper.PaymentMapper;
import main008.BED.review.entity.Review;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.mapper.UsersMapper;
import main008.BED.wish.dto.WishDto;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.mapper.WishMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Validated
public class ContentsController {

    private final ContentsService contentsService;

    private final ChapterService chapterService;
    private final UploadClassService uploadClassService;
    private final ContentsMapper contentsMapper;
    private final UsersMapper usersMapper;
    private final PaymentMapper paymentMapper;
    private final DocsMapper docsMapper;
    private final ReviewMapper reviewMapper;
    private final WishMapper wishMapper;
    private final S3Service s3Service;


    // 컨텐츠 개설
    @PostMapping("/auth/{users-id}/uploadcontents")
    public ResponseEntity postContents(@PathVariable("users-id") @Positive Long usersId,
                                       @RequestParam("title") String title,
                                       @RequestParam("categories") Contents.Categories categories,
                                       @RequestParam("details") String details,
                                       @RequestParam("tutorDetail") String tutorDetail,
                                       @RequestParam("thumbnail") MultipartFile thumbnail,
                                       @RequestParam("price") Integer price) {

        // thumbnail -> S3 업로드
        HashMap map = s3Service.uploadToS3(thumbnail, "/contents/thumbnail");
        String fileKey = map.get("fileKey").toString();
        String thumbnailUrl = map.get("url").toString();

        PaymentDto.Post paymentPost = new PaymentDto.Post(price);

        Payment payment = paymentMapper.postToEntity(paymentPost);

        ContentsDto.Post post = new ContentsDto.Post(title, categories, details, tutorDetail, thumbnailUrl, fileKey);

        Contents contents = contentsService.createContents(contentsMapper.postToContents(post), usersId, payment);

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

    /**
     * READ: 컨텐츠 상세화면 Response DTO
     */
    // TODO: 구매 여부에 따라 상세화면 Dto 구분 로직 작성
    @GetMapping("/auth/contents/{contents-id}")
    public ResponseEntity getContent(@PathVariable("contents-id") @Positive Long contentsId) {

        Contents contents = contentsService.readContent(contentsId);

        ChapterDto.CurriculumInContent curriculumInContent
                = chapterService.readCurriculumInContent(contentsId);

        ContentsDto.ResponseInContent responseInContent
                = new ContentsDto.ResponseInContent(contentsId,
                contents.getTitle(),
                contents.getThumbnail(),
                contents.getLikesCount(),
                contents.getCategories(),
                contents.getDetails(),
                contents.getTutorDetail(),
                curriculumInContent.getCurriculumInfo());

        return new ResponseEntity(responseInContent, HttpStatus.OK);
    }

    /**
     * READ: 영상 재생 화면 Response DTO
     */
    @GetMapping("/auth/contents/{contents-id}/video/{uploadClass-id}")
    public ResponseEntity getStream(@PathVariable("contents-id") @Positive Long contentsId,
                                    @PathVariable("uploadClass-id") @Positive Long uploadClassId) {

        Contents contents = contentsService.readContent(contentsId);
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);
        ChapterDto.CurriculumInStream curriculumInStream = chapterService.readCurriculumInStream(contentsId);

        Users users = contents.getUsers();
        String title = contents.getTitle();
        Docs docs = uploadClass.getDocs();
        String video = uploadClass.getVideo();
        List<Review> reviewList = uploadClass.getReviewList();

        ContentsDto.ResponseForStream responseForStream
                = new ContentsDto.ResponseForStream(
                usersMapper.usersToUserResponseDto(users),
                title,
                video,
                docsMapper.entityToResponseDto(docs),
                reviewMapper.listEntityToListResponseDto(reviewList),
                curriculumInStream.getCurriculumInfo());

        return new ResponseEntity(responseForStream, HttpStatus.OK);
    }

    /**
     * 카테고리 조회
     */
    @GetMapping("/category/sort")
    public ResponseEntity getCategories(@RequestParam("categories") Contents.Categories categories,
                                        @RequestParam(name = "sort", required = false, defaultValue = "likesCount") String sort,
                                        @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        Page<Contents> contents = contentsService.findContentsByCategory(page, size, categories, sort);

        return new ResponseEntity<>(contentsMapper.contentsToResponses(contents.getContent(), usersMapper), HttpStatus.OK);
    }
}
