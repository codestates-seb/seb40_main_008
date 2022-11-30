package main008.BED.likes.repository;

import main008.BED.likes.entity.LikesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikesDetailRepository extends JpaRepository<LikesDetail, Long> {

    @Query(value =
            "SELECT * FROM likes_detail l " +
            "WHERE l.likes_id = :likes_id " +
            "AND liked = true"
            , nativeQuery = true)
    Optional<List<LikesDetail>> findByLikesTrue(Long likes_id);

    @Query(value =
              "SELECT * FROM likes_Detail "
            + "WHERE likes_Detail.users_id = :users_id "
            + "AND likes_Detail.likes_id = :likes_id"
            , nativeQuery = true)
    Optional<LikesDetail> findByUsersLikes(Long users_id, Long likes_id);
}
