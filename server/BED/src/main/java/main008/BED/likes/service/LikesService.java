package main008.BED.likes.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.exception.BusinessLogicException;
import main008.BED.exception.ExceptionCode;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.repository.LikesDetailRepository;
import main008.BED.likes.repository.LikesRepository;
import main008.BED.likes.service.statePattern.LikesButton;
import main008.BED.users.entity.Users;
import main008.BED.users.service.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {

    private final ContentsRepository contentsRepository;
    private final LikesRepository likesRepository;
    private final LikesDetailRepository likesDetailRepository;
    private final UsersService usersService;
    public static LikesButton likesButton = new LikesButton();

    public void createLikes(Contents contents) {

        Likes likes = contents.getLikes();

        likes.setLikesCount(0);
        likes.setContents(contents);
        likes.setLikesDetails(new ArrayList<>());

        likesRepository.save(likes);
    }

    /**
     * readOnly = true
     *
     * 이 속성을 @Transactional 에 설정하게되면 읽기전용 트랜잭션이 된다.
     *
     * 읽기 전용 트랜잭션으로 설정하는 이유?
     * 일반 @Transactional은 해당하는 메서드가 호출될 때 JPA에서 commit이 호출된다.
     * JPA에서 commit이 호출되면 영속성 컨텍스트에서 flush처리를 하게되고 변경 감지를 하여 snapshot을 생성하게 된다.
     *
     * 조회 메서드에서는 불필요한 기능들이 동작하게 되는 것이다.
     * 즉, 조회메서드에는 readOnly = true를 적용시켜주어 JPA 성능을 최적화 시켜주는 것이 좋다.
     */
    @Transactional(readOnly = true)
    public List<LikesDetail> findTrueLikes(Likes likes) {

        return likesDetailRepository.findByLikesTrue(likes.getLikesId()).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.LIKES_NOT_FOUND));
    }

    /**
     * 컨텐츠 좋아요 기능
     *
     * 트랜잭션 격리레벨 : 트랜잭션은 다른 트랙잭션에 영향을 주지 않고, 독립적으로 실행되는 격리성이 보장되어야한다.
     * isolation 어트리뷰트를 사용하여 격리 조정 옵션 지정이 가능하다.
     *
     * isolation = Isolation.SERIALIZABLE : 동일한 데이터에 대해 동시에 둘 이상의 트랜잭션 수행이 불가하도록 한다.
     * 하지만 DB에서 많이 사용하지 않음
     * why?
     * 동시 처리 성능 최하위이기 때문
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Likes likesContents(Long contentsId, Users users, Boolean liked) {

        Contents contents = contentsRepository.findByContentsId(contentsId).orElseThrow(()
                -> new BusinessLogicException(ExceptionCode.CONTENTS_NOT_FOUND));

        Likes likes = contents.getLikes();

        LikesDetail likesDetail = new LikesDetail();
        likesDetail.setLiked(false);
        likesDetail.setLikes(likes);

        ifLikesHave(users, likes, contents, likesDetail);

        return likesRepository.save(likes);
    }

    /*좋아요 기능 조건문*/
    private void ifLikesHave(Users users, Likes likes, Contents contents, LikesDetail likesDetail) {

        LikesDetail likesDetail1 = likesDetailRepository.findByUsersLikes(users.getUsersId(), likes.getLikesId()).get();

        if (likesDetail1 != null) {

            // State Pattern
            likesButton.clickButton(likesButton, likes, likesDetail1, contents, likesRepository, contentsRepository);

        } else {

            likesClick(likesDetail, likes, contents, users);
        }
    }

    /*처음 좋아요를 누른 경우*/
    private void likesClick(LikesDetail likesDetail, Likes likes, Contents contents, Users users) {

        likesDetail.setLikes(likes);
        likesDetail.setLiked(true);
        likesDetail.setUsers(users);
        likesDetail.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        likes.setContents(contents);
        likes.getLikesDetails().add(likesDetail);
        likes.setLikesCount(likes.getLikesCount() + 1);
        likesRepository.save(likes);
//        contentsRepository.likesCountForContentsUp(contents.getContentsId());
    }

    // 컨텐츠 좋아요 유무 찾기
    public Boolean getContentLike(Contents contents, Principal principal) {

        if (principal == null) {
            return false;
        }

        Users users = usersService.findVerifiedUserByEmail(principal.getName());

        if (contents.getLikes().getLikesDetails().size() == 0) {return false;}
        else {
            if (likesDetailRepository.findByUsersLikes(users.getUsersId(), contents.getLikes().getLikesId()).isEmpty()) {return false;}
            else {
                LikesDetail likesDetail = likesDetailRepository.findByUsersLikes(users.getUsersId(), contents.getLikes().getLikesId()).get();

                return likesDetail.getLiked();
            }
        }
    }
}
