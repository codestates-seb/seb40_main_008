package main008.BED.bookmark.controller;

import lombok.RequiredArgsConstructor;
import main008.BED.bookmark.dto.BookmarkDto;
import main008.BED.bookmark.mapper.BookmarkMapper;
import main008.BED.bookmark.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private final BookmarkMapper bookmarkMapper;


    /**
     * POST: 메모 작성
     */
    @PostMapping("auth/bookmark/{uploadclass-id}")
    public ResponseEntity postBookmark(Principal principal,
                                       @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                       @RequestBody @Valid BookmarkDto.Post post) {

        bookmarkService.saveBookmark(bookmarkMapper.postDtoToEntity(post), principal, uploadClassId);

        return new ResponseEntity("The Bookmark is saved.", HttpStatus.OK);
    }

    /**
     * PATCH: 메모 수정
     */
    @PatchMapping("auth/bookmark/{uploadclass-id}/{bookmark-id}")
    public ResponseEntity patchBookmark(Principal principal,
                                        @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                        @PathVariable("bookmark-id") @Positive Long bookmarkId,
                                        @RequestBody @Valid BookmarkDto.Patch patch) {

        bookmarkService.updateBookmark(
                bookmarkMapper.patchDtoToEntity(patch),
                principal,
                uploadClassId,
                bookmarkId);

        return new ResponseEntity<>("The Bookmark is updated.", HttpStatus.OK);
    }


    /**
     * DELETE: 메모 삭제
     */
    @DeleteMapping("auth/bookmark/{uploadclass-id}/{bookmark-id}")
    public ResponseEntity deleteBookmark(Principal principal,
                                         @PathVariable("uploadclass-id") @Positive Long uploadClassId,
                                         @PathVariable("bookmark-id") @Positive Long bookmarkId) {

        bookmarkService.removeBookmark(principal, uploadClassId, bookmarkId);

        return new ResponseEntity<>("The Memo is removed.", HttpStatus.OK);
    }
}
