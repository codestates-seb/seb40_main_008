package main008.BED.wish.service.statePattern;

import main008.BED.contents.entity.Contents;
import main008.BED.myClass.entity.MyClass;
import main008.BED.wish.repository.WishRepository;

public class WishButton {

    private WishState wishState = new WishStateTrue();

    public void setWishState(WishState wishState) {
        this.wishState = wishState;
    }

    public void clickButton(WishButton wishButton, WishRepository wishRepository, MyClass myClass, Contents contents) {

        wishState.clickWish(wishButton, wishRepository, myClass, contents);
    }
}
