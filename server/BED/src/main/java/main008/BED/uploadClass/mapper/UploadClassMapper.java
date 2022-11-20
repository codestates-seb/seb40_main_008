package main008.BED.uploadClass.mapper;

import main008.BED.uploadClass.dto.UploadClassDto;
import main008.BED.uploadClass.entity.UploadClass;
import org.mapstruct.Mapper;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface UploadClassMapper {

    default UploadClass postDtoToEntity(UploadClassDto.Post post) throws IOException {
        return new UploadClass().builder()
                .video(post.getVideo())
                .title(post.getTitle())
                .chapter(post.getChapter())
                .docs(post.getDocs())
                .fileKey(post.getFileKey())
                .name(post.getName())
                .build();
    }

    default UploadClass patchDtoToEntity(UploadClassDto.Patch patch) throws IOException {
        return new UploadClass().builder()
                .video(patch.getVideo())
                .title(patch.getTitle())
                .chapter(patch.getChapter())
                .docs(patch.getDocs())
                .fileKey(patch.getFileKey())
                .name(patch.getName())
                .build();
    }

    UploadClassDto.ResponseDtoInChapter entityToResponseDtoInChapter(UploadClass uploadClass);

}
