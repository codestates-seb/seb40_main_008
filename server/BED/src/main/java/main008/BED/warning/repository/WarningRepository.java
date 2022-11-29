package main008.BED.warning.repository;

import main008.BED.warning.entity.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarningRepository extends JpaRepository<Warning, Long> {


    @Query(value = "SELECT warning " +
            "FROM Warning  warning " +
            "WHERE warning.users.usersId = :usersId")
    List<Warning> findByUsersId(@Param("usersId") Long usersId);

}
