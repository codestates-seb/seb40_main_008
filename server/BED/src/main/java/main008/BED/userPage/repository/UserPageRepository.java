package main008.BED.userPage.repository;

import main008.BED.userPage.entity.UserPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPageRepository extends JpaRepository<UserPage, Long> {
}
