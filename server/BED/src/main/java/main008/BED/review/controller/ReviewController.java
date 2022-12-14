package main008.BED.review.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.review.dto.ReviewDto;
import main008.BED.review.entity.Review;
import main008.BED.review.mapper.ReviewMapper;
import main008.BED.review.service.ReviewService;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping("/auth/uploadclass/{uploadclass-id}")
    public ResponseEntity postReview(Principal principal,
                                     @RequestBody @Valid ReviewDto.Post post,
                                     @PathVariable("uploadclass-id") @Positive Long uploadClassId) {

        reviewService.verifyTutorUser(principal, uploadClassId);

        Review review = reviewMapper.postDtoToEntity(post);

        reviewService.saveReview(review, principal, uploadClassId);

        return new ResponseEntity<>("The Review is saved.", HttpStatus.OK);
    }

    @PatchMapping("/auth/uploadclass/{uploadclass-id}/{review-id}")
    public ResponseEntity patchReview(Principal principal,
                                      @RequestBody @Valid ReviewDto.Patch patch,
                                      @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                      @PathVariable("review-id") @Positive Long reviewId) {

        reviewService.verifyReqUser(reviewId, principal);

        Review newReview = reviewMapper.patchDtoToEntity(patch);

        reviewService.updateReview(newReview, principal, uploadClassId, reviewId);

        return new ResponseEntity<>("The Review is updated.", HttpStatus.OK);

    }

    @DeleteMapping("/auth/uploadclass/{uploadclass-id}/{review-id}")
    public ResponseEntity deleteReview(Principal principal,
                                       @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                       @PathVariable("review-id") @Positive Long reviewId) {

        reviewService.verifyReqUser(reviewId, principal);
        reviewService.removeReview(principal, uploadClassId, reviewId);

        return new ResponseEntity<>("Review is removed.", HttpStatus.OK);
    }
}
