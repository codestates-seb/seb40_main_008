package main008.BED.myUploadClass.mapper;

import main008.BED.myUploadClass.dto.MyUploadClassDto;
import main008.BED.myUploadClass.entity.MyUploadClass;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MyUploadClassMapper {

    MyUploadClassDto.Response myUploadClassToResponse(MyUploadClass myUploadClass);
}
