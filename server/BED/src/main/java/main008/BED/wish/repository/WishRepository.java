package main008.BED.wish.repository;

import main008.BED.wish.entity.Wish;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
//
    List<Wish> findByMyClassMyClassId(Long myClassId);
//
//    List<Wish> findByWishedTrueAndMyClassMyClassId(Long myClassId);
}
