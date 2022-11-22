package main008.BED.review.service;

import lombok.RequiredArgsConstructor;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UsersRepository usersRepository;
    private final UploadClassRepository uploadClassRepository;


    /**
     * SAVE: 리뷰 저장
     */
    public Review saveReview(Review review, Long usersId, Long uploadClassId) {
        // TODO: 예외처리 추가

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
}
