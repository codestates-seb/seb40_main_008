package main008.BED.myUploadClass.mapper;

import main008.BED.contents.dto.ContentsDto;
import main008.BED.myUploadClass.dto.MyUploadClassDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyUploadClassMapper {

    default MyUploadClassDto.Response myUploadClassToResponse(List<ContentsDto.MUCResponse> mucResponses) {

        return new MyUploadClassDto.Response(mucResponses);
    }
}
