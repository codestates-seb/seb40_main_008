package main008.BED.takingClass.repository;

import main008.BED.takingClass.entity.TakingClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TakingClassRepository extends JpaRepository<TakingClass, Long> {
}
