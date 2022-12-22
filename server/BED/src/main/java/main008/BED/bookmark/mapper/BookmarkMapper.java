package main008.BED.bookmark.mapper;

import main008.BED.bookmark.dto.BookmarkDto;
import main008.BED.bookmark.entity.Bookmark;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookmarkMapper {

    Bookmark postDtoToEntity(BookmarkDto.Post post);

    Bookmark patchDtoToEntity(BookmarkDto.Patch patch);

    default BookmarkDto.Response entityToResponseDto(Bookmark bookmark) {
        return new BookmarkDto.Response()
                .builder()
                .usersId(bookmark.getUsers().getUsersId())
                .bookmarkId(bookmark.getId())
                .memo(bookmark.getMemo())
                .createdAt(bookmark.getCreatedAt())
                .modifiedAt(bookmark.getModifiedAt())
                .timeLine(bookmark.getTimeLine())
                .build();
    }

    default List<BookmarkDto.Response> listEntityToListResponseDto(List<Bookmark> bookmarkList) {

        return bookmarkList.stream()
                .map(bookmark -> this.entityToResponseDto(bookmark))
                .collect(Collectors.toList());
    }
}
