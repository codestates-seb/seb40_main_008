package main008.BED.docs.mapper;

import main008.BED.docs.dto.DocsDto;
import main008.BED.docs.entity.Docs;
import org.mapstruct.Mapper;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface DocsMapper {

    default Docs postDtoToEntity(DocsDto.Post post) throws IOException {
        return new Docs().builder()
                .data(post.getFile().getBytes())
                .details(post.getDetails())
                .name(post.getFile().getOriginalFilename())
                .build();
    }
}
