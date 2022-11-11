package main008.BED.my_class.repository;

import main008.BED.my_class.entity.MyClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyClassRepository extends JpaRepository<MyClass, Long> {
}
