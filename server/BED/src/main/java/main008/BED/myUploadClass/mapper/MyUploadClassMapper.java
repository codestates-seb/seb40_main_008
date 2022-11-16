package main008.BED.myUploadClass.mapper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.contents.entity.Contents;
import main008.BED.myUploadClass.dto.MyUploadClassDto;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.users.dto.UsersDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyUploadClassMapper {

    default MyUploadClassDto.Response myUploadClassToResponse(MyUploadClass myUploadClass) {

        return new MyUploadClassDto.Response(
                List.of(
                        new ContentsDto.Response(
                                21L,
                                "title21",
                                "title21 thumbnail",
                                Contents.Categories.PROGRAMMING,
                                new UsersDto.UserResponseToHome(
                                        1L,
                                        "User1",
                                        "User1 profileImage")),
                        new ContentsDto.Response(
                                16L,
                                "title16",
                                "title16 thumbnail",
                                Contents.Categories.PROGRAMMING,
                                new UsersDto.UserResponseToHome(
                                        1L,
                                        "User1",
                                        "User1 profileImage")),
                        new ContentsDto.Response(
                                5L,
                                "title15",
                                "title5 thumbnail",
                                Contents.Categories.PROGRAMMING,
                                new UsersDto.UserResponseToHome(
                                        1L,
                                        "User1",
                                        "User1 profileImage"))));
    }
}
