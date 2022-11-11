package main008.BED.content.repository;

import main008.BED.content.entity.Contents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Contents, Long> {
}
