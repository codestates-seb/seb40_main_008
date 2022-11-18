package main008.BED.likes.repository;

import main008.BED.likes.entity.LikesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesDetailRepository extends JpaRepository<LikesDetail, Long> {

    LikesDetail findByUsersUsersId(Long usersId);
}
