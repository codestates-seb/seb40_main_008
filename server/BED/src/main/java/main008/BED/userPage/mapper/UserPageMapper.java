package main008.BED.userPage.mapper;

import main008.BED.userPage.dto.UserPageDto;
import main008.BED.userPage.entity.UserPage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPageMapper {

    UserPageDto.Response userPageToResponse(UserPage userPage);
}
