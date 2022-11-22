package main008.BED.review.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.review.entity.Review;
import main008.BED.review.repository.ReviewRepository;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.repository.UploadClassRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;
    private final UploadClassRepository uploadClassRepository;

    private static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    /**
     * SAVE: 리뷰 저장
     */
    public Review saveReview(Review review, Long usersId, Long uploadClassId) {

        validReviewService(review, usersId, uploadClassId);
        Users user = usersRepository.findByUsersId(usersId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();

        review.setUsers(user);
        review.setUploadClass(uploadClass);
        review.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        List<Review> reviewList = uploadClass.getReviewList();
        reviewList.add(review);
        uploadClass.setReviewList(reviewList);

        return reviewRepository.save(review);
    }

    /**
     * UPDATE: 리뷰 수정
     */
    public void updateReview(Review newReview, Long usersId, Long uploadClassId, Long oldReviewId) {

        validReviewService(newReview, usersId, uploadClassId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
        Review oldReview = reviewRepository.findById(oldReviewId).get();

        if (!oldReview.getUsers().getUsersId().equals(usersId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        oldReview.setModifiedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        oldReview.setComments(newReview.getComments());
        oldReview.setStarRate(newReview.getStarRate());
    }

    /**
     * REMOVE: 리뷰 삭제
     */
    public void removeReview(Long usersId, Long uploadClassId, Long reviewId) {

        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        else if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }

        Review review = reviewRepository.findById(reviewId).get();
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();
        if (!review.getUsers().getUsersId().equals(usersId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        uploadClass.getReviewList().remove(review);
        reviewRepository.delete(review);
    }

    /**
     * Validation
     */
    private void validReviewService(Review newReview, Long usersId, Long uploadClassId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }
        else if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        } else if (!list.contains(newReview.getStarRate())) {
            throw new BusinessLogicException(ExceptionCode.BAD_STAR_RATE);
        }
    }
}
