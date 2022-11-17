package main008.BED.uploadClass.mapper;

import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import org.mapstruct.Mapper;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface UploadClassMapper {

    default UploadClass postDtoToEntity(UploadClassDto.Post post) throws IOException {
        return new UploadClass().builder()
                .video(post.getVideo().getBytes())
                .title(post.getTitle())
                .chapter(post.getChapter())
                .docs(post.getDocs())
                .name(post.getVideo().getOriginalFilename())
                .build();
    }

    UploadClassDto.ResponseDtoInChapter entityToResponseDtoInChapter(UploadClass uploadClass);

}
