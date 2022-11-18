package main008.BED.chapter.repository;

import main008.BED.chapter.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    Chapter findByChapterId(Long chapterId);
}
