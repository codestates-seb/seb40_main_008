package main008.BED.wish.service.statePattern;

import main008.BED.contents.entity.Contents;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.myClass.entity.MyClass;
import main008.BED.wish.entity.Wish;
import main008.BED.wish.repository.WishRepository;

// 찜 기능 상태 인터페이스
public interface WishState {

    // 찜 기능 및 취소해줄 메서드 구현
     void clickWish(WishButton wishButton, WishRepository wishRepository, MyClass myClass, Contents contents);
}

class WishStateFalse implements WishState {

    @Override
    public void clickWish(WishButton wishButton, WishRepository wishRepository, MyClass myClass, Contents contents) {

        Wish wish = wishRepository.findByMyClassIdAndContentsId(
                myClass.getMyClassId(), contents.getContentsId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        wish.setWished(true);
        wishRepository.save(wish);
        wishButton.setWishState(new WishStateTrue());
    }
}

class WishStateTrue implements WishState {

    @Override
    public void clickWish(WishButton wishButton, WishRepository wishRepository, MyClass myClass, Contents contents) {

        Wish wish = wishRepository.findByMyClassIdAndContentsId(
                myClass.getMyClassId(), contents.getContentsId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.WISH_NOT_FOUND));

        wish.setWished(false);
        wishRepository.save(wish);
        wishButton.setWishState(new WishStateFalse());
    }
}