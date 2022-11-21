package main008.BED.likes.repository;

import main008.BED.likes.entity.LikesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesDetailRepository extends JpaRepository<LikesDetail, Long> {

    List<LikesDetail> findByLikesLikesId(Long likesId);

    LikesDetail findByUsersUsersIdAndLikesLikesId(Long usersId, Long likesId);

    List<LikesDetail> findByLikesLikesIdAndLikedTrue(Long likesId);
}
