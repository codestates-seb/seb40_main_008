package main008.BED.mainPage.mapper;

import main008.BED.contents.mapper.ContentsMapper;
import main008.BED.mainPage.dto.MainPageDto;
import main008.BED.mainPage.entity.MainPage;
import main008.BED.users.mapper.UsersMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainPageMapper {

    default MainPageDto.NotLoginResponse mainPageToNotLoginResponse(MainPage mainPage,
                                                                    ContentsMapper contentsMapper,
                                                                    UsersMapper usersMapper) {

        return MainPageDto.NotLoginResponse.builder()
                .contentsList(contentsMapper.contentsToResponses(mainPage.getContentsList(),usersMapper))
                .build();

    }

    default MainPageDto.LoginResponse mainPageToLoginResponse(MainPage mainPage, ContentsMapper contentsMapper,
                                                              Long usersId, UsersMapper usersMapper ) {

        return MainPageDto.LoginResponse.builder()
                .usersId(usersId)
                .contentsList(contentsMapper.contentsToResponses(mainPage.getContentsList(), usersMapper))
                .build();
    }
}
