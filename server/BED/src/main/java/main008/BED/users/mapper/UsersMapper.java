package main008.BED.users.mapper;

import main008.BED.users.dto.UsersDto;
import main008.BED.users.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto.UserResponseToHome usersToResponse(Users users);
    default UsersDto.UserResponseToMyPage usersToMyPage(Users users) {

        return UsersDto.UserResponseToMyPage.builder()
                .usersId(users.getUsersId())
                .userName(users.getUserName())
                .email(users.getEmail())
                .profileImage(users.getProfileImage())
                .totalCoin(users.getTotalCoin())
                .build();
    }
    default Users postToEntity(UsersDto.Post post) {

        return Users.builder()
                .userName(post.getUserName())
                .email(post.getEmail())
                .profileImage(post.getProfileImage())
                .totalCoin(5000)
                .build();
    }

    Users usersPatchDtoToUser(UsersDto.Patch userPatchDto);
    UsersDto.Response usersToUserResponseDto(Users users);
}
