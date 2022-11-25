package main008.BED.likes.repository;

import main008.BED.likes.entity.Likes;
import main008.BED.likes.entity.LikesDetail;
import main008.BED.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesDetailRepository extends JpaRepository<LikesDetail, Long> {

    List<LikesDetail> findByLikesLikesIdAndLikedTrue(Long likesId);

    @Query(value =
              "SELECT * FROM likes_Detail "
            + "WHERE likes_Detail.users_id = :users_id "
            + "AND likes_Detail.likes_id = :likes_id"
            , nativeQuery = true)
    LikesDetail findByUsersIdLikesId(Long users_id, Long likes_id);
}
