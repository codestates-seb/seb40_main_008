package main008.BED.wishClass.repository;

import main008.BED.wishClass.entity.WishClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishClassRepository extends JpaRepository<WishClass, Long> {
}
