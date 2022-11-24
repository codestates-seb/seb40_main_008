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
