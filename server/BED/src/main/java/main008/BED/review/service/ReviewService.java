package main008.BED.review.service;

import lombok.RequiredArgsConstructor;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.review.entity.Review;
import main008.BED.review.repository.ReviewRepository;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UsersService usersService;
    private final UploadClassService uploadClassService;
    private static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

    /**
     * SAVE: 리뷰 저장
     */
    public Review saveReview(Review review, Principal principal, Long uploadClassId) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);

        validReviewService(review);

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
    public void updateReview(Review newReview, Principal principal, Long uploadClassId, Long oldReviewId) {

        validReviewService(newReview);

        Users users = usersService.findVerifiedUserByEmail(principal.getName());
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);
        Review oldReview = reviewRepository.findById(oldReviewId).get();

        if (!oldReview.getUsers().getUsersId().equals(users.getUsersId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        oldReview.setModifiedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        oldReview.setComments(newReview.getComments());
        oldReview.setStarRate(newReview.getStarRate());
    }

    /**
     * REMOVE: 리뷰 삭제
     */
    public void removeReview(Principal principal, Long uploadClassId, Long reviewId) {

        Users users = usersService.findVerifiedUserByEmail(principal.getName());
        Review review = reviewRepository.findById(reviewId).get();
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);

        if (!review.getUsers().getUsersId().equals(users.getUsersId())) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        uploadClass.getReviewList().remove(review);
        reviewRepository.delete(review);
    }

    /**
     * Validation
     */
    private void validReviewService(Review newReview) {
       if (!list.contains(newReview.getStarRate())) {
            throw new BusinessLogicException(ExceptionCode.BAD_STAR_RATE);
        }
    }

    /**
     * FIND
     */
    public Long findReviewerId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return review.getUsers().getUsersId();
    }

    /**
     * 강좌 게시자 판별
     */
    public void verifyTutorUser(Principal principal, Long uploadClassId) {

        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);

        Long reqUserId = usersService.findVerifiedUserByEmail(principal.getName()).getUsersId();
        Long tutorId = uploadClass.getChapter().getContents().getUsers().getUsersId();

        if (Objects.equals(reqUserId, tutorId)) { // tutor는 본인 강의에 대하여 리뷰 불가능
            throw new BusinessLogicException(ExceptionCode.FORBIDDEN_TUTOR);
        }
    }

    /**
     * 리뷰 작성자 판별
     */
    public void verifyReqUser(Long reviewId, Principal principal) {

        Long reviewerId = findReviewerId(reviewId);
        Long reqUserId = usersService.findVerifiedUserByEmail(principal.getName()).getUsersId();

        if (!Objects.equals(reqUserId, reviewerId)) { // 리뷰 작성자만 수정/삭제 가능
            throw new BusinessLogicException(ExceptionCode.FORBIDDEN_USER);
        }
    }
}

