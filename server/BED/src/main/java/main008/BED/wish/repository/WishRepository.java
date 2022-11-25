package main008.BED.wish.repository;

import main008.BED.wish.entity.Wish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {
    Optional<List<Wish>> findByMyClassMyClassId(Long myClassId);

    List<Wish> findByMyClassMyClassIdAndWishedTrue(Long myClassId);

    @Query(value =
              "SELECT * FROM wish "
            + "WHERE wish.my_class_id = :my_class_id "
            + "AND wish.contents_id = :contents_id"
            , nativeQuery = true)
    Optional<Wish> findByMyClassIdAndContentsId(Long my_class_id, Long contents_id);
}
