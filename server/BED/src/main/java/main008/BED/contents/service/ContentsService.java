package main008.BED.contents.service;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.entity.Chapter;
import main008.BED.chapter.exception.ContentsNotFoundException;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.repository.LikesDetailRepository;
import main008.BED.likes.repository.LikesRepository;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.repository.PaymentRepository;
import main008.BED.payment.service.PaymentService;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentsService {

    private final ContentsRepository contentsRepository;
    private final UsersRepository usersRepository;
    private final WishRepository wishRepository;
    private final MyUploadClassRepository myUploadClassRepository;
    private final MyClassRepository myClassRepository;
    private final LikesRepository likesRepository;

    private final S3ServiceImpl s3ServiceImpl;
    private final PaymentService paymentService;


    // contents 올리기
    public Contents createContents(Contents contents, Long usersId, Payment payment) {

        contents.setWishes(new ArrayList<>());
        contents.setLikes(new Likes());

        Likes likes = contents.getLikes();
        likes.setLikesCount(0);
        likes.setContents(contents);
        likes.setLikesDetails(new ArrayList<>());
        likesRepository.save(likes);

        Contents contents1 = contentsRepository.save(contents);

        Users users = usersRepository.findByUsersId(usersId);
        contents1.setUsers(users);

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersUsersId(usersId);
        List<Contents> contentsList = myUploadClass.getContentsList();
        contentsList.add(contents1);
        myUploadClass.setContentsList(contentsList);
        myUploadClassRepository.save(myUploadClass);

        payment.setContents(contents1);
        paymentService.createPaymentWithContent(payment);

        return contents1;
    }


    /**
     * contents 삭제
     */
    public void removeContents(Long contentId) {
        // TODO: 컨텐츠에 담긴 챕터, DOCS 연쇄 삭제
        Contents byContentsId = contentsRepository.findByContentsId(contentId);
        String fileKey = byContentsId.getFileKey();
        s3ServiceImpl.delete(fileKey, "/contents/thumbnail");
        contentsRepository.delete(byContentsId);
    }


    /**
     * Read: 커리큘럼 (챕터 목록 끌어오기)
     */
    public List<Chapter> readChapterList(Long contentsId) {
        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new RuntimeException("The Content with this id is not found.");
        }
        Contents contents = contentsRepository.findByContentsId(contentsId);
        return contents.getChapterList();
    }



    public Page<Contents> getContentsPage(int page, int size) {

        Pageable pageable =
                PageRequest.of(page - 1, size,
                        Sort.by("likesCount").descending());

        return contentsRepository.findAll(pageable);
    }

    // 콘텐츠 찜 기능
    public void wishContents(Long contentsId, Long usersId, Wish wish) {

        Contents contents = contentsRepository.findByContentsId(contentsId);
        MyClass myClass = myClassRepository.findByUsersUsersId(usersId);
        List<Wish> wish1 = wishRepository.findByMyClassMyClassId(myClass.getMyClassId());

        for (Wish wish2 : wish1) {

            if (wish2.getContents() == contents) {

                wishRepository.delete(wish2);
            }

            if (wish2.getContents() == null) {

                wish.setContents(contents);
                wish.setWished(true);
                wishRepository.save(wish);
                myClass.addWish(wish);
                myClassRepository.save(myClass);
                contents.addWish(wish);
                contentsRepository.save(contents);
            }
            else {

                if (wish2.getContents() == contents && wish2.getWished() == true) {

                    Wish wish3 = wishRepository.findByMyClassMyClassIdAndContentsContentsId(myClass.getMyClassId(), contents.getContentsId());

                    List<Wish> wishes = wishRepository.findAll();

                    if (wishes.size() < 2) {
                        Wish wish4 = new Wish();
                        wish4.setMyClass(myClass);
                        wishRepository.save(wish4);
                    }
                    wishRepository.delete(wish3);
                }
                else {

                    wish.setWished(true);
                    wish.setMyClass(myClass);
                    wish.setContents(contents);
                    wishRepository.save(wish);
                    myClass.addWish(wish);
                    myClassRepository.save(myClass);
                    contents.addWish(wish);
                    contentsRepository.save(contents);
                }
            }
        }

//        return "Added to wishlist.";
    }

    /**
     * Read: Content One
     */
    public Contents readContent(Long contentsId) {
        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND);
        }
        return contentsRepository.findByContentsId(contentsId);
    }

    /**
     * Search: 강의명 검색 - 최신순
     */
    public Page<Contents> searchTitleContentsByLateast(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("contentsId").descending());

        List<Contents> contentsList = contentsRepository.findContentsByTitleContainingOrderByContentsIdDesc(keyword);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), contentsList.size());
        Page<Contents> contentsPage = new PageImpl<>(contentsList.subList(start, end), pageable, contentsList.size());

        return contentsPage;
    }

    /**
     * find Categories
     */
    public Page<Contents> findContentsByCategory(int page, int size, Contents.Categories categories, String sort) {

        if ("newest".equals(sort)) {
            sort = "contentsId";
        }

        return contentsRepository.findByCategories(
                categories, PageRequest.of(page - 1, size, Sort.by(sort).descending()));
    }

    /**
     * Search: 강의명 검색 - 인기순
     */
    public Page<Contents> searchTitleContentsByPopular(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("likesCount").descending());

        List<Contents> contentsList = contentsRepository.findContentsByTitleContainingOrderByLikesCountDesc(keyword);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), contentsList.size());
        Page<Contents> contentsPage = new PageImpl<>(contentsList.subList(start, end), pageable, contentsList.size());

        return contentsPage;
    }

}
