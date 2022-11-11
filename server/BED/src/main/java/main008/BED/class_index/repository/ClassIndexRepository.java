package main008.BED.class_index.repository;

import main008.BED.class_index.entity.ClassIndex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassIndexRepository extends JpaRepository<ClassIndex, Long> {

}
