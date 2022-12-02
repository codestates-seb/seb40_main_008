package main008.BED.bookmark.service;

import lombok.RequiredArgsConstructor;
import main008.BED.bookmark.entity.Bookmark;
import main008.BED.bookmark.repository.BookmarkRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.repository.UploadClassRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.warning.entity.Warning;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final UsersRepository usersRepository;
    private final UploadClassRepository uploadClassRepository;


    /**
     * SAVE: 메모 하기
     */
    public void saveBookmark(Bookmark bookmark, Long usersId, Long uploadClassId) {

        existsById(usersId, uploadClassId);

        Users user = usersRepository.findByUsersId(usersId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();

        bookmark.setUsers(user);
        bookmark.addUploadClass(uploadClass);
        bookmark.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        bookmarkRepository.save(bookmark);
    }

    private void existsById(Long usersId, Long uploadClassId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        } else if (!uploadClassRepository.existsByUploadClassId(uploadClassId)) {
            throw new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND);
        }
    }

    /**
     * UPDATE: 메모 수정하기
     */
    public void updateBookmark(Bookmark newBookmark, Long usersId, Long uploadCLassId, Long oldBookmarkId) {

        existsById(usersId, uploadCLassId);

        if (!bookmarkRepository.existsById(oldBookmarkId)) {
            throw new BusinessLogicException(ExceptionCode.BOOKMARK_NOT_FOUND);
        }

        Bookmark oldBookmark = bookmarkRepository.findById(oldBookmarkId).get();

        if (!oldBookmark.getUsers().getUsersId().equals(usersId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        // update
        oldBookmark.setModifiedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        oldBookmark.setMemo(newBookmark.getMemo());
        oldBookmark.setTimeLine(newBookmark.getTimeLine());

    }

    /**
     * REMOVE: 메모 삭제하기
     */
    public void removeBookmark(Long usersId, Long uploadClassId, Long bookmarkId) {

        existsById(usersId, uploadClassId);

        if (!bookmarkRepository.existsById(bookmarkId)) {
            throw new BusinessLogicException(ExceptionCode.BOOKMARK_NOT_FOUND);
        }

        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).get();

        if (!bookmark.getUsers().getUsersId().equals(usersId)) {
            throw new BusinessLogicException(ExceptionCode.UNAUTHORIZED);
        }

        bookmarkRepository.delete(bookmark);

//        Users user = usersRepository.findByUsersId(usersId);
        UploadClass uploadClass = uploadClassRepository.findById(uploadClassId).get();

        List<Bookmark> list = new ArrayList<>();
//        user.setBookmarkList(list);
        uploadClass.setBookmarkList(list);
    }

    /**
     * READ: 유저 아이디에 해당하는 북마크 내역 목록 조회 (Version 2: Query)
     */
    public List<Bookmark> findBookmarkListByUsersId(Long usersId) {
        if (!usersRepository.existsById(usersId)) {
            throw new BusinessLogicException(ExceptionCode.USER_NOT_FOUND);
        }

        List<Bookmark> bookmarkListByUsersId = bookmarkRepository.findByUsersId(usersId);
        return bookmarkListByUsersId;
    }
}
