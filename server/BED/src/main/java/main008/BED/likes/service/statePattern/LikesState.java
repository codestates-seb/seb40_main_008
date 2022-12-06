package main008.BED.likes.service.statePattern;

import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.repository.LikesRepository;

// 좋아요 상태 인터페이스
public interface LikesState {

    // 좋아요를 클릭 및 취소해줄 메서드 구현
    void clickLike(LikesButton likesButton, Likes likes, LikesDetail likesDetail, Contents contents,
                   LikesRepository likesRepository, ContentsRepository contentsRepository);
}

class LikeStateFalse implements LikesState {

    @Override
    public void clickLike(LikesButton likesButton, Likes likes, LikesDetail likesDetail, Contents contents,
                          LikesRepository likesRepository, ContentsRepository contentsRepository) {

        likesDetail.setLiked(true);
        likes.setLikesCount(likes.getLikesCount() + 1);
        likesRepository.save(likes);
//        contentsRepository.likesCountForContentsUp(contents.getContentsId());
        likesButton.setLikeState(new LikeStateTrue());
    }
}

class LikeStateTrue implements LikesState {

    @Override
    public void clickLike(LikesButton likesButton, Likes likes, LikesDetail likesDetail, Contents contents,
                          LikesRepository likesRepository, ContentsRepository contentsRepository) {

        likesDetail.setLiked(false);
        likes.setLikesCount(likes.getLikesCount() - 1);
        likesRepository.save(likes);
//        contentsRepository.likesCountForContentsDown(contents.getContentsId());
        likesButton.setLikeState(new LikeStateFalse());
    }
}