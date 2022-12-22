package main008.BED.review.mapper;

import main008.BED.review.dto.ReviewDto;
import main008.BED.review.entity.Review;
import org.mapstruct.Mapper;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review postDtoToEntity(ReviewDto.Post post);

    Review patchDtoToEntity(ReviewDto.Patch patch);

    default ReviewDto.Response entityToResponse(Review review) {
        if ( review == null ) {
            return null;
        }

        Long reviewId = null;
        int starRate = 0;
        String comments = null;
        ZonedDateTime createdAt = null;
        ZonedDateTime modifiedAt = null;
        Long usersId = null;
        String userName = null;
        String profileImage = null;

        reviewId = review.getReviewId();
        starRate = review.getStarRate();
        comments = review.getComments();
        createdAt = review.getCreatedAt();
        modifiedAt = review.getModifiedAt();
        usersId = review.getUsers().getUsersId();
        userName = review.getUsers().getUserName();
        profileImage = review.getUsers().getProfileImage();


        ReviewDto.Response response = new ReviewDto.Response( usersId, userName, profileImage, reviewId, starRate, comments, createdAt, modifiedAt );

        return response;
    }

    default List<ReviewDto.Response> listEntityToListResponseDto(List<Review> reviewList) {

        return reviewList.stream()
                .map(review -> this.entityToResponse(review))
                .collect(Collectors.toList());
    }
}
