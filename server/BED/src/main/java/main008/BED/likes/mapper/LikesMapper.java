package main008.BED.likes.mapper;

import main008.BED.likes.dto.LikesDto;
import main008.BED.likes.entity.Likes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikesMapper {

    Likes postToLikes(LikesDto.Post post);
    LikesDto.Response likesToResponse(Likes likes);
}
