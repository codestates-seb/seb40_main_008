package main008.BED.docs.mapper;

import main008.BED.docs.dto.DocsDto;
import main008.BED.docs.entity.Docs;
import org.mapstruct.Mapper;

import java.io.IOException;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DocsMapper {

    default Docs postDtoToEntity(DocsDto.Post post) throws IOException {
        return new Docs().builder()
                .data(post.getFile().getBytes())
                .details(post.getDetails())
                .name(post.getFile().getOriginalFilename())
                .build();
    }

    default Docs patchDtoToEntity(DocsDto.Patch patch) throws IOException {
        return new Docs().builder()
                .data(patch.getFile().getBytes())
                .details(patch.getDetails())
                .name(patch.getFile().getOriginalFilename())
                .build();
    }

    DocsDto.ReadAllDto entityToReadAllDto(Docs docs);

    DocsDto.Response entityToResponseDto(Docs docs);

}
