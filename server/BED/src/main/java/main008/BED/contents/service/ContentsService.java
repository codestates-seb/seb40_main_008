package main008.BED.contents.service;

import lombok.RequiredArgsConstructor;
import main008.BED.S3.S3ServiceImpl;
import main008.BED.chapter.entity.Chapter;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.repository.LikesRepository;
import main008.BED.myClass.entity.MyClass;
import main008.BED.myClass.repository.MyClassRepository;
import main008.BED.myUploadClass.entity.MyUploadClass;
import main008.BED.myUploadClass.repository.MyUploadClassRepository;
import main008.BED.payment.entity.Payment;
import main008.BED.payment.service.PaymentService;
import main008.BED.review.entity.Review;
import main008.BED.uploadClass.entity.UploadClass;
import main008.BED.uploadClass.repository.UploadClassRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private final UploadClassRepository uploadClassRepository;
    private final S3ServiceImpl s3ServiceImpl;
    private final PaymentService paymentService;
    
    
    // contents 올리기
    public Contents createContents(Contents contents, Long usersId, Payment payment) {

        contents.setWishes(new ArrayList<>());
        contents.setLikes(new Likes());
        contents.setLikesCount(0);

        setLikes(contents);

        contents = contentsRepository.save(contents);

        Users users = usersRepository.findByUsersId(usersId);
        contents.setUsers(users);

        setMyUploadClass(usersId, contents);

        payment.setContents(contents);
        paymentService.createPaymentWithContent(payment);

        return contents;
    }
    
    /**
     * contents 삭제
     */
    public void removeContents(Long contentId) {

        // TODO: 컨텐츠에 담긴 챕터, DOCS 연쇄 삭제
        Contents byContentsId = contentsRepository.findByContentsId(contentId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

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

        Contents contents = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        return contents.getChapterList();
    }

    @Transactional(readOnly = true)
    public Page<Contents> getContentsPage(int page, int size) {

        Pageable pageable =
                PageRequest.of(page - 1, size,
                        Sort.by("likesCount").descending());

        return contentsRepository.findAll(pageable);
    }

    /**
     * 콘텐츠 찜 기능
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void wishContents(Long contentsId, Long usersId, Wish wish) {

        Contents contents = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        MyClass myClass = myClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));

        List<Wish> wishList = wishRepository.findByMyClassId(myClass.getMyClassId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        for (Wish wishIndex : wishList) {
            ifWishHas(wishIndex, contents, myClass);
        }
    }
    
    /**
     * Read: Content One
     */
    @Transactional(readOnly = true)
    public Contents readContent(Long contentsId) {

        if (!contentsRepository.existsByContentsId(contentsId)) {
            throw new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND);
        }

        return contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));
    }

    
    /**
     * Search: 강의명 검색 - 최신순
     */
    public Page<Contents> searchTitleContentsByLateast(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("contentsId").descending());

        List<Contents> contentsList = contentsRepository.findContentsByTitleContainingOrderByContentsIdDesc(keyword);

        ArrayList<Contents> discloseList = getDiscloseContents(contentsList);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), discloseList.size());
        Page<Contents> contentsPage = new PageImpl<>(discloseList.subList(start, end), pageable, discloseList.size());

        return contentsPage;
    }

    
    /**
     * find Categories
     */
    public Page<Contents> findContentsByCategory(int page, int size, Contents.Categories categories, String sort) {

        if ("newest".equals(sort)) {
            sort = "contentsId";
        } else if ("popular".equals(sort)){
            sort = "likesCount";
        }

        return contentsRepository.findByCategories(categories,
                PageRequest.of(page - 1, size, Sort.by(sort).descending()));
        }

        
    /**
     * Search: 강의명 검색 - 인기순
     */
    public Page<Contents> searchTitleContentsByPopular(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("likesCount").descending());

        List<Contents> contentsList = contentsRepository.findContentsByTitleContainingOrderByLikesCountDesc(keyword);

        ArrayList<Contents> discloseList = getDiscloseContents(contentsList);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), discloseList.size());
        Page<Contents> contentsPage = new PageImpl<>(discloseList.subList(start, end), pageable, discloseList.size());

        return contentsPage;
    }

    /*찜 기능 조건문*/
    private void ifWishHas(Wish wish, Contents contents, MyClass myClass) {

        if (wish.getContents() == null) {
            firstWishContent(wish, contents, myClass);
        }
        else {
            secondIfWishHad(wish, contents, myClass);
        }
    }

    private void secondIfWishHad(Wish wish, Contents contents, MyClass myClass) {

        if (wish.getContents() == contents && wish.getWished()) {
            reWishContent(myClass, contents);
        }
        else {
            reCancelWish(wish, myClass, contents);
        }
    }

    /*찜한 적 없는 컨텐츠일 때*/
    private void firstWishContent(Wish wish, Contents contents, MyClass myClass) {

        wish.setContents(contents);
        wish.setMyClass(myClass);
        wish.setWished(true);
        wishRepository.save(wish);

        myClass.addWish(wish);
        myClassRepository.save(myClass);

        contents.addWish(wish);
        contentsRepository.save(contents);
    }

    /*찜한 적 있는 컨텐츠일 때*/
    private void reWishContent(MyClass myClass, Contents contents) {

        Wish wish = wishRepository.findByMyClassIdAndContentsId(
                myClass.getMyClassId(), contents.getContentsId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        List<Wish> wishes = wishRepository.findAll();

        if (wishes.size() < 2) {
            Wish wish1 = new Wish();
            wish1.setMyClass(myClass);
            wishRepository.save(wish1);
        }
        wishRepository.delete(wish);
    }

    /*찜했다가 취소한 적 있는 컨텐츠일 때*/
    private void reCancelWish(Wish wish, MyClass myClass, Contents contents) {

        wish.setWished(true);
        wish.setMyClass(myClass);
        wish.setContents(contents);
        wishRepository.save(wish);

        myClass.addWish(wish);
        myClassRepository.save(myClass);

        contents.addWish(wish);
        contentsRepository.save(contents);
    }

    /*컨텐츠 올리기 set likes*/
    private void setLikes(Contents contents) {

        Likes likes = contents.getLikes();

        likes.setLikesCount(0);
        likes.setContents(contents);
        likes.setLikesDetails(new ArrayList<>());

        likesRepository.save(likes);
    }

    /*컨텐츠 올리기 set myUploadClass*/
    private void setMyUploadClass(Long usersId, Contents contents) {

        MyUploadClass myUploadClass = myUploadClassRepository.findByUsersId(usersId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.UPLOAD_CLASS_NOT_FOUND));

        List<Contents> contentsList = myUploadClass.getContentsList();

        contentsList.add(contents);
        myUploadClass.setContentsList(contentsList);

        myUploadClassRepository.save(myUploadClass);
    }


    /**
     * 총 별점 계산
     */
    public double calculateAvgStar(Long contentsId) {

        Contents content = contentsRepository.findByContentsId(contentsId).get();


        List<Chapter> chapterList = content.getChapterList();
        if (chapterList == null) {
            return 0;
        }

        List<List<UploadClass>> collect = chapterList.stream()
                .map(chapter -> chapter.getUploadClassList())
                .collect(Collectors.toList());

        int sum = 0;
        int count = 0;

        for (List<UploadClass> uploadClassList : collect) {
            for (UploadClass uploadClass : uploadClassList) {
                List<Review> reviewList = uploadClass.getReviewList();
                for (Review review : reviewList) {
                    sum += review.getStarRate();
                    count++;
                }
            }
        }

        if (count == 0) { // Review가 0개인 경우
            return 0;
        } else {
            float avg = (float) sum / count;
            return Math.round(avg * 100) / 100.0; // 소숫점 둘 째 자리까지
        }

    }

    private static ArrayList<Contents> getDiscloseContents(List<Contents> contentsList) {
        ArrayList<Contents> discloseList = new ArrayList<>();

        for (Contents content : contentsList) {
            Boolean disclosure = content.getDisclosure();
            if (disclosure) {
                discloseList.add(content);
            }
        }
        return discloseList;
    }


}
