package main008.BED.review.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.review.dto.ReviewDto;
import main008.BED.review.entity.Review;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    // TODO 1: 댓글 - 평점은 어디서 입력할 것인지? -> 일대일 매칭하여 입력, 평균 별점은 강의 구매 전에 표시, 강의마다 평점
    // TODO 2: 댓글 - 댓글의 좋아요 기능 삭제? -> 어드밴스드로 남겨둠
    // TODO 3: 수업자료 - Docs 원본 파일 이름을 dto로 전송 -> 다운로드 버튼 클릭 시 클라이언트 로컬 저장소에 저장-> 알단 이대로


    @PostMapping("/auth/{users-id}/uploadclass/{uploadclass-id}")
    public ResponseEntity postReview(@RequestBody @Valid ReviewDto.Post post,
                                     @PathVariable("users-id") @Positive Long usersId,
                                     @PathVariable("uploadclass-id") @Positive Long upladClassId) {
        // TODO: @Valid 검증 내용 추가

        Review review = reviewMapper.postDtoToEntity(post);
        reviewService.saveReview(review, usersId, upladClassId);
        return new ResponseEntity("The Review is saved.", HttpStatus.OK);
    }

    @PatchMapping("/auth/{users-id}/uploadclass/{uploadclass-id}/{review-id}")
    public ResponseEntity patchReview(@RequestBody @Valid ReviewDto.Patch patch,
                                      @PathVariable("users-id") @Positive Long usersId,
                                      @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                      @PathVariable("review-id") @Positive Long reviewId) {

        Review newReview = reviewMapper.patchDtoToEntity(patch);

        reviewService.updateReview(newReview, usersId, uploadClassId, reviewId);

        return new ResponseEntity("The Review is updated.", HttpStatus.OK);

    }

    @DeleteMapping("/auth/{users-id}/uploadclass/{uploadclass-id}/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("users-id") @Positive Long usersId,
                                       @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                       @PathVariable("review-id") @Positive Long reviewId) {
        reviewService.removeReview(usersId, uploadClassId, reviewId);
        return new ResponseEntity("Review is removed.", HttpStatus.OK);
    }
}
