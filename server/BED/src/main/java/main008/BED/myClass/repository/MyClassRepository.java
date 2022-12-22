package main008.BED.myClass.repository;

import main008.BED.myClass.entity.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyClassRepository extends JpaRepository<MyClass, Long> {

    @Query(value =
            "SELECT * FROM my_class " +
            "WHERE my_class.users_id = :users_id"
            , nativeQuery = true)
    Optional<MyClass> findByUsersId(Long users_id);
}
