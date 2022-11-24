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
        List<LikesDetail> likesDetails = likesDetailRepository.findByLikesLikesId(likes.getLikesId());


        if (likesDetailRepository.findByUsersUsersIdAndLikesLikesId(usersId, likes.getLikesId()) != null) {

            LikesDetail likesDetail1 = likesDetailRepository.findByUsersUsersIdAndLikesLikesId(usersId, likes.getLikesId());

            if (likesDetail1.getLiked()) {

                Likes likes1 = likesDetail1.getLikes();

                likesDetail1.setLiked(false);
                likesDetailRepository.save(likesDetail1);
                likes1.setLikesCount(likes1.getLikesCount() - 1);

                contents.setLikesCount(likes1.getLikesCount());
                contentsRepository.save(contents);

            } else {

                Likes likes1 = likesDetail1.getLikes();

                likesDetail1.setLiked(true);
                likesDetailRepository.save(likesDetail1);
                likes1.setLikesCount(likes1.getLikesCount() + 1); // DB에서

                contents.setLikesCount(likes1.getLikesCount());
                contentsRepository.save(contents);
            }
        } else {

            likesDetail.setLikes(likes);
            likesDetail.setUsers(users);
            likesDetail.setLiked(true);
            likesDetail.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));

            likes.getLikesDetails().add(likesDetail);
            likes.setLikesCount(likes.getLikesCount() + 1);

            contents.setLikesCount(likes.getLikesCount());
            contentsRepository.save(contents);
        }
        return likes;
    }
}
