package main008.BED.contents.repository;

import main008.BED.contents.entity.Contents;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentsRepository extends JpaRepository<Contents, Long> {

    Optional<Contents> findByContentsId(Long contentsId);

    boolean existsByContentsId(Long contentsId);

    @Query(value =
            "SELECT * FROM contents " +
            "WHERE contents.users_id = :users_id"
            , nativeQuery = true)
    Optional<List<Contents>> findByUsersId(Long users_id);

    List<Contents> findContentsByTitleContainingOrderByContentsIdDesc(String keyword);

    List<Contents> findContentsByTitleContainingOrderByLikesCountDesc(String keyword);

    @Query(value =
            "SELECT * FROM contents c " +
            "WHERE c.categories = :categories " +
            "ORDER BY contents_id DESC"
            , nativeQuery = true)
    List<Contents> categoryNewestSort(String categories);

    @Query(value =
            "SELECT * FROM contents c " +
            "WHERE c.categories = :categories " +
            "ORDER BY likes_count DESC, contents_id DESC"
            , nativeQuery = true)
    List<Contents> categoryPopularSort(String categories);

    /**
     * clearAutomatically = true
     *
     * DB 값이 변경 (SELECT, UPDATE, INSERT, DELETE)될 때
     * Service 로직의 @Transactional 으로 인해 생겨난 영속성 컨텍스트의 값과 다를 경우
     * 자동으로 영속성 컨텍스트의 값을 DB와 동기화 시켜준다.
     *
     * 이 속성을 적용하지 않을 시
     *
     * em.clear // 영속성 컨텍스트 초기화
     *
     * 를 서비스 로직에 추가시킨 후 쿼리를 날리면 된다.
     *
     * flushAutomatically() = true 는 em.flush와 같은 역할을 한다.
     *
     * em.flush // 영속성 컨텍스트 커밋
     */
    @Modifying(clearAutomatically = true)
    @Query(value =
            "UPDATE contents " +
            "SET likes_Count = likes_Count + 1 " +
            "WHERE contents.contents_id = :contents_id"
            , nativeQuery = true)
    void likesCountForContentsUp(Long contents_id);

    @Modifying(clearAutomatically = true) // nativeQuery = true : 기존 sql문 사용 가능하게 해줌
    @Query(value =
            "UPDATE contents " +
            "SET likes_Count = likes_Count - 1"+
            "WHERE contents.contents_id = :contents_id"
            , nativeQuery = true)
    void likesCountForContentsDown(Long contents_id);

}
