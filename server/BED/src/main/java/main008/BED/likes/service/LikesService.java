package main008.BED.likes.service;

import lombok.RequiredArgsConstructor;
import main008.BED.contents.entity.Contents;
import main008.BED.contents.repository.ContentsRepository;
import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.likes.repository.LikesDetailRepository;
import main008.BED.likes.repository.LikesRepository;
import main008.BED.users.entity.Users;
import main008.BED.users.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional // 이해 필요
@RequiredArgsConstructor
public class LikesService {

    private final ContentsRepository contentsRepository;
    private final UsersRepository usersRepository;
    private final LikesRepository likesRepository;
    private final LikesDetailRepository likesDetailRepository;

    public List<LikesDetail> findTrueLikes(Likes likes) {

        return likesDetailRepository.findByLikesLikesIdAndLikedTrue(likes.getLikesId());
    }

    // 콘텐츠 좋아요 기능
    public Likes likesContents(Long contentsId, Long usersId, LikesDetail likesDetail) {

        Contents contents = contentsRepository.findByContentsId(contentsId);
        Users users = usersRepository.findByUsersId(usersId);
        Likes likes = contents.getLikes();

        if (likesDetailRepository.findByUsersIdLikesId(
                        usersId, likes.getLikesId()) != null) {

            LikesDetail likesDetail1 = likesDetailRepository
                            .findByUsersIdLikesId(usersId, likes.getLikesId());

            reLikesClick(likesDetail1, likes, contents);
        } else {

            likesClick(likesDetail, likes, contents, users);
        }

        return likesRepository.save(likes);
    }

    /**
     * 이미 좋아요 누른 경우
     */
    private void reLikesClick(LikesDetail likesDetail, Likes likes, Contents contents) {

        if (likesDetail.getLiked()) {

            likesDetail.setLiked(false);
            likesRepository.likesCountDown(likes);
            contentsRepository.likesCountForContentsDown(contents);
        } else {

            likesDetail.setLiked(true);
            likesRepository.likesCountUp(likes);
            contentsRepository.likesCountForContentsUp(contents);
        }

        likesDetailRepository.save(likesDetail);
    }

    /**
     * 처음 좋아요를 누른 경우
     */
    private void likesClick(LikesDetail likesDetail, Likes likes, Contents contents, Users users) {

        likesDetail.setLikes(likes);
        likesDetail.setUsers(users);
        likesDetail.setLiked(true);
        likesDetail.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

        likes.getLikesDetails().add(likesDetail);
        likes.setContents(contents);
        likesRepository.likesCountUp(likes);
        contentsRepository.likesCountForContentsUp(contents);
    }
}
