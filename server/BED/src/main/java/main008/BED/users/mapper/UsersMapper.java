package main008.BED.users.mapper;

import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto.Response usersToResponse(Users users);
}
