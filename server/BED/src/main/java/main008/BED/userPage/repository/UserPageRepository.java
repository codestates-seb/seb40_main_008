package main008.BED.userPage.repository;

import main008.BED.userPage.entity.UserPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPageRepository extends JpaRepository<UserPage, Long> {

    @Query(value =
            "SELECT * FROM user_page u " +
            "WHERE u.users_id = :users_id"
            , nativeQuery = true)
    Optional<UserPage> findByUsersId(Long users_id);
}
