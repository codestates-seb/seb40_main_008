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

    MyUploadClassDto.Response myUploadClassToResponse(MyUploadClass myUploadClass);
}
