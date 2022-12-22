package main008.BED.bookmark.service;

import lombok.RequiredArgsConstructor;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.bookmark.repository.BookmarkRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.service.UploadClassService;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UploadClassService uploadClassService;
    private final UsersService usersService;


    /**
     * SAVE: 메모 하기
     */
    public void saveBookmark(Bookmark bookmark, Principal principal, Long uploadClassId) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);

        bookmark.setUsers(user);
        bookmark.addUploadClass(uploadClass);
        bookmark.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        bookmarkRepository.save(bookmark);
    }

    /**
     * UPDATE: 메모 수정하기
     */
    public void updateBookmark(Bookmark newBookmark, Principal principal, Long uploadClassId, Long oldBookmarkId) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());
        uploadClassService.readClassById(uploadClassId);

        existBookmark(oldBookmarkId);

        Bookmark oldBookmark = bookmarkRepository.findById(oldBookmarkId).get();

        existUser(oldBookmark, user.getUsersId());

        // update
        oldBookmark.setModifiedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        oldBookmark.setMemo(newBookmark.getMemo());
        oldBookmark.setTimeLine(newBookmark.getTimeLine());

    }

    /**
     * REMOVE: 메모 삭제하기
     */
    public void removeBookmark(Principal principal, Long uploadClassId, Long bookmarkId) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());
        UploadClass uploadClass = uploadClassService.readClassById(uploadClassId);

        existBookmark(bookmarkId);

        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).get();

        existUser(bookmark, user.getUsersId());

        bookmarkRepository.delete(bookmark);

        List<Bookmark> list = new ArrayList<>();
//        user.setBookmarkList(list);
        uploadClass.setBookmarkList(list);
    }

    /**
     * READ: 유저 아이디에 해당하는 북마크 내역 목록 조회 (Version 2: Query)
     */
    public List<Bookmark> findBookmarkListByUsersId(Principal principal, Long uploadClassId) {

        Users user = usersService.findVerifiedUserByEmail(principal.getName());

        return bookmarkRepository.findByUsersIdAndUploadClassId(user.getUsersId(), uploadClassId);
    }

    private void existBookmark(Long bookmarkId) {

        if (!bookmarkRepository.existsById(bookmarkId)) {
            throw new BusinessLogicException(ExceptionCode.BOOKMARK_NOT_FOUND);
        }
    }

    private void existUser(Bookmark bookmark, Long userId) {

        if (!bookmark.getUsers().getUsersId().equals(userId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }
    }
}
