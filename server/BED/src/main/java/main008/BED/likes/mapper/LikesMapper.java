package main008.BED.likes.mapper;

import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.likes.dto.LikesDto;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LikesMapper {

    default LikesDto.Response likesToResponse(Likes likes, List<LikesDetail> likesDetail, LikesDetailMapper likesDetailMapper, ContentsMapper contentsMapper) {

        return new LikesDto.Response(
                contentsMapper.contentsToResponse(likes.getContents()),
                likesDetailMapper.entityToResponse(likesDetail)
        );
    }
}
