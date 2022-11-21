package main008.BED.likes.mapper;

import main008.BED.likes.dto.LikesDetailDto;
import main008.BED.likes.entity.LikesDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikesDetailMapper {

    LikesDetail postToEntity(LikesDetailDto.Post p);

    List<LikesDetailDto.Response> entityToResponse(List<LikesDetail> likesDetails);
}
