package main008.BED.users.repository;

import main008.BED.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsersId(Long usersId);

    Users findByEmail(String email);

    boolean existsByEmail(String email);
}
