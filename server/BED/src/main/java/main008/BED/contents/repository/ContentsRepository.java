package main008.BED.contents.repository;

import main008.BED.contents.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {

    Contents findByContentsId(Long contentsId);

    boolean existsByContentsId(Long contentsId);

    List<Contents> findByUsersUsersId(Long usersId);

    Page<Contents> findByCategories(Contents.Categories categories, Pageable pageable);

    List<Contents> findContentsByTitleContainingOrderByContentsIdDesc(String keyword);

    List<Contents> findContentsByTitleContainingOrderByLikesCountDesc(String keyword);

    @Modifying(clearAutomatically = true) // DB값과 영속성 컨텍스트 값이 다를 경우 일치시켜준다
    @Query(value = "UPDATE contents SET likes_Count = likes_Count + 1", nativeQuery = true)
    void likesCountForContentsUp(Contents contents);

    @Modifying(clearAutomatically = true) // nativeQuery = true : 기존 sql문 사용 가능하게 해줌
    @Query(value = "UPDATE contents SET likes_Count = likes_Count - 1", nativeQuery = true)
    void likesCountForContentsDown(Contents contents);

}
