package main008.BED.likes.service.statePattern;

import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.repository.LikesRepository;

public class LikesButton {

    private LikesState likeState = new LikeStateTrue();

    public void setLikeState(LikesState likeState) {
        this.likeState = likeState;
    }

    public void clickButton(LikesButton likesButton, Likes likes, LikesDetail likesDetail, Contents contents,
                            LikesRepository likesRepository, ContentsRepository contentsRepository) {

        likeState.clickLike(likesButton, likes, likesDetail, contents, likesRepository, contentsRepository);
    }
}
