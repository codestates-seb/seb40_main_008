package main008.BED.myClass.repository;

import main008.BED.myClass.entity.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyClassRepository extends JpaRepository<MyClass, Long> {

    Optional<MyClass> findByUsersUsersId(Long usersId);
}
