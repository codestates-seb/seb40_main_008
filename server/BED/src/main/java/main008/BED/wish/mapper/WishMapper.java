package main008.BED.wish.mapper;

import main008.BED.wish.dto.WishDto;
import main008.BED.wish.entity.Wish;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WishMapper {

    default Wish postToWish(WishDto.Post post) {

        Wish wish = new Wish();
        wish.setWished(Boolean.valueOf(post.getWished()));

        return wish;
    }

}
