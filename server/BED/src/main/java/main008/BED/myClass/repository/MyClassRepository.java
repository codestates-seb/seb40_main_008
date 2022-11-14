package main008.BED.myClass.repository;

import main008.BED.myClass.entity.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyClassRepository extends JpaRepository<MyClass, Long> {
}
