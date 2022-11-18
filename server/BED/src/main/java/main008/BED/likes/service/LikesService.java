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

    // 콘텐츠 좋아요 기능
    public Likes likesContents(Long contentsId, Long usersId, Likes liked) {

        Contents contents = contentsRepository.findByContentsId(contentsId);
        Users users = usersRepository.findByUsersId(usersId);
        Likes likes = contents.getLikes();
        List<LikesDetail> likesDetails = likes.getLikesDetails();

        for (LikesDetail likesDetail : likesDetails) {

            if (likesDetail.getUsers().equals(users)) {

                if (liked.getLiked()) {

                    likes.setCount(likes.getCount() - 1);
                    likes.getLikesDetails().remove(likesDetailRepository.findByUsersUsersId(usersId));
                }
            } else if (likesDetail.getUsers() == null) {


            } else if (!likesDetail.getUsers().equals(users)) {

                if (liked.getLiked()) {

                    likes.setCount(likes.getCount() + 1);

                    LikesDetail likesDetail1 = new LikesDetail();
                    likesDetail1.setLikes(likes);
                    likesDetail1.setCreatedAt(ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
                    likes.getLikesDetails().add(likesDetail1);

                    likesRepository.save(likes);
                }
            }
        }

        return likesRepository.save(likes);
    }
}
