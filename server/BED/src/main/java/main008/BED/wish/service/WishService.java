package main008.BED.wish.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WishService {

    private final WishRepository wishRepository;

    public Wish createWish(MyClass myClass) {

        Wish wish = new Wish();
        wish.setWished(false);
        wish.setMyClass(myClass);
        return wishRepository.save(wish);
    }

    public List<Wish> findWish(Long myClassId) {

        return wishRepository.findByMyClassId(myClassId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));
    }

    public List<Wish> findTrueWishes(Long myClassId) {

        return wishRepository.findByMyClassIdAndTrue(myClassId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));
    }

    /*찜한 적 없는 컨텐츠일 때*/
    public Wish firstWishContent(Wish wish, Contents contents, MyClass myClass, Boolean wishTrue) {

        wish.setContents(contents);
        wish.setMyClass(myClass);
        wish.setWished(wishTrue);

        return wishRepository.save(wish);
    }

    /*찜한 적 있는 컨텐츠일 때*/
    public void reWishContent(MyClass myClass, Contents contents) {

        Wish wish1 = wishRepository.findByMyClassIdAndContentsId(
                myClass.getMyClassId(), contents.getContentsId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        wish1.setWished(false);

        wishRepository.save(wish1);
    }

    /*찜했다가 취소한 적 있는 컨텐츠일 때*/
    public Wish reCancelWish(Wish wish, MyClass myClass, Contents contents) {

        wish.setWished(true);
        wish.setMyClass(myClass);
        wish.setContents(contents);

        return wishRepository.save(wish);
    }
}
