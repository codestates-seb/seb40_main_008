package main008.BED.review.mapper;

import main008.BED.review.dto.ReviewDto;
import main008.BED.review.entity.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    Review postDtoToEntity(ReviewDto.Post post);
}
