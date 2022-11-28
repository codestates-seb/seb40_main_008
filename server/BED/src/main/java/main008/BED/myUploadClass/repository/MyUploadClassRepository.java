package main008.BED.myUploadClass.repository;

import main008.BED.myUploadClass.entity.MyUploadClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUploadClassRepository extends JpaRepository<MyUploadClass, Long> {

    @Query(value =
            "SELECT * FROM my_upload_class m " +
            "WHERE m.users_id = :users_id"
            , nativeQuery = true)
    Optional<MyUploadClass> findByUsersId(Long users_id);
}
