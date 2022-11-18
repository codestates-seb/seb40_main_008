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
@Transactional
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

        for (LikesDetail likesDetail2 : likesDetails) {

            if (likesDetail2.getUsers() == users) {

                likesDetailRepository.delete(likesDetail2);

            }

            if (likesDetail2.getUsers() == null) {

                LikesDetail likesDetail1 = new LikesDetail();
                likesDetail1.setLikes(likes);
                likesDetail1.setUsers(users);
                likesDetail1.setLiked(true);
                likesDetail1.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
                likesDetailRepository.save(likesDetail1);

                likes.getLikesDetails().add(likesDetail1);
                likes.setCount(likes.getCount() + 1);
                likesRepository.save(likes);
            } else {

                if (likesDetail2.getUsers() == users && likesDetail2.getLiked() == true) {

                    LikesDetail l = likesDetailRepository.findByUsersUsersId(usersId);

                    likesDetailRepository.delete(l);
                } else {

                    likesDetail.setLikes(likes);
                    likesDetail.setUsers(users);
                    likesDetail.setLiked(true);
                    likesDetail.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
                    likesDetailRepository.save(likesDetail);

                    likes.getLikesDetails().add(likesDetail);
                    likes.setCount(likes.getCount() + 1);
                    likesRepository.save(likes);
                }
            }
        }
        return likes;
    }
}
